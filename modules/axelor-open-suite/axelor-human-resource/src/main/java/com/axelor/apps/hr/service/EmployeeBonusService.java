/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2022 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.hr.service;

import com.axelor.app.internal.AppFilter;
import com.axelor.apps.base.db.Period;
import com.axelor.apps.hr.db.Employee;
import com.axelor.apps.hr.db.EmployeeBonusMgt;
import com.axelor.apps.hr.db.EmployeeBonusMgtLine;
import com.axelor.apps.hr.db.HRConfig;
import com.axelor.apps.hr.db.repo.EmployeeBonusMgtLineRepository;
import com.axelor.apps.hr.db.repo.EmployeeBonusMgtRepository;
import com.axelor.apps.hr.db.repo.EmployeeRepository;
import com.axelor.apps.hr.exception.IExceptionMessage;
import com.axelor.apps.hr.service.employee.EmployeeServiceImpl;
import com.axelor.exception.AxelorException;
import com.axelor.exception.db.repo.TraceBackRepository;
import com.axelor.exception.service.TraceBackService;
import com.axelor.i18n.I18n;
import com.axelor.inject.Beans;
import com.axelor.tool.template.TemplateMaker;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.codehaus.groovy.control.customizers.ImportCustomizer;

public class EmployeeBonusService {

  @Inject EmployeeBonusMgtRepository employeeBonusMgtRepo;

  @Inject EmployeeBonusMgtLineRepository employeeBonusMgtLineRepo;

  @Inject EmployeeServiceImpl employeeService;

  @Inject EmployeeComputeDaysLeaveBonusService employeeComputeDaysLeaveBonusService;

  private static final char TEMPLATE_DELIMITER = '$';

  @Transactional(rollbackOn = {Exception.class})
  public void compute(EmployeeBonusMgt bonus) throws AxelorException {
    Map<Employee, EmployeeBonusMgtLine> employeeStatus = new HashMap<>();
    for (EmployeeBonusMgtLine line : bonus.getEmployeeBonusMgtLineList()) {
      employeeStatus.put(line.getEmployee(), line);
    }

    List<Employee> allEmployee =
        Beans.get(EmployeeRepository.class)
            .all()
            .filter("self.mainEmploymentContract.payCompany = ?1", bonus.getCompany())
            .fetch();
    TemplateMaker maker =
        new TemplateMaker(
            bonus.getCompany().getTimezone(),
            AppFilter.getLocale(),
            TEMPLATE_DELIMITER,
            TEMPLATE_DELIMITER);
    String eval;
    CompilerConfiguration conf = new CompilerConfiguration();
    ImportCustomizer customizer = new ImportCustomizer();
    customizer.addStaticStars("java.lang.Math");
    conf.addCompilationCustomizers(customizer);
    Binding binding = new Binding();
    GroovyShell shell = new GroovyShell(binding, conf);

    Integer employeeBonusStatus = EmployeeBonusMgtRepository.STATUS_CALCULATED;
    for (Employee employee : allEmployee) {

      // check if line is already calculated
      if (employeeStatus.get(employee) != null) {
        if (employeeStatus
            .get(employee)
            .getStatusSelect()
            .equals(EmployeeBonusMgtLineRepository.STATUS_CALCULATED)) {
          continue;
        } else {
          bonus.removeEmployeeBonusMgtLineListItem(employeeStatus.get(employee));
        }
      }

      maker.setContext(employee, "Employee");
      EmployeeBonusMgtLine line = new EmployeeBonusMgtLine();
      line.setEmployeeBonusMgt(bonus);
      line.setEmployee(employee);
      maker.addInContext("EmployeeBonusMgtLine", line);
      String formula = bonus.getEmployeeBonusType().getApplicationCondition();
      Integer lineStatus = EmployeeBonusMgtLineRepository.STATUS_CALCULATED;
      try {
        formula =
            replaceExpressionInFormula(
                formula, bonus.getCompany().getHrConfig(), employee, bonus.getPayPeriod());
      } catch (Exception e) {
        TraceBackService.trace(e);
        formula = "true";
        lineStatus = EmployeeBonusMgtLineRepository.STATUS_ANOMALY;
      }
      maker.setTemplate(formula);
      eval = maker.make();

      if (shell.evaluate(eval).toString().equals("true")) {
        try {
          formula =
              replaceExpressionInFormula(
                  bonus.getEmployeeBonusType().getFormula(),
                  bonus.getCompany().getHrConfig(),
                  employee,
                  bonus.getPayPeriod());
        } catch (Exception e) {
          lineStatus = EmployeeBonusMgtLineRepository.STATUS_ANOMALY;
        }

        line.setStatusSelect(lineStatus);

        if (lineStatus.equals(EmployeeBonusMgtLineRepository.STATUS_ANOMALY)) {
          employeeBonusStatus = EmployeeBonusMgtRepository.STATUS_ANOMALY;
          employeeBonusMgtLineRepo.save(line);
          continue;
        }

        line.setSeniorityDate(employee.getSeniorityDate());
        line.setCoef(employee.getBonusCoef());
        line.setWeeklyPlanning(employee.getWeeklyPlanning());

        maker.setTemplate(formula);
        eval = maker.make();
        line.setAmount(new BigDecimal(shell.evaluate(eval).toString()));

        employeeBonusMgtLineRepo.save(line);
      }
    }
    bonus.setStatusSelect(employeeBonusStatus);
    employeeBonusMgtRepo.save(bonus);
  }

  public String replaceExpressionInFormula(
      String formula, HRConfig hrConfig, Employee employee, Period period) throws AxelorException {

    if (!Strings.isNullOrEmpty(hrConfig.getAgeVariableName())) {
      formula =
          formula.replace(
              hrConfig.getAgeVariableName(),
              String.valueOf(employeeService.getAge(employee, period.getFromDate())));
    }
    if (!Strings.isNullOrEmpty(hrConfig.getSeniorityVariableName())) {
      formula =
          formula.replace(
              hrConfig.getSeniorityVariableName(),
              String.valueOf(employeeService.getLengthOfService(employee, period.getFromDate())));
    }
    if (!Strings.isNullOrEmpty(hrConfig.getWorkingDaysVariableName())) {
      formula =
          formula.replace(
              hrConfig.getWorkingDaysVariableName(),
              String.valueOf(
                  employeeComputeDaysLeaveBonusService.getDaysWorkedInPeriod(
                      employee, period.getFromDate(), period.getToDate())));
    }
    if (!Strings.isNullOrEmpty(hrConfig.getTotalWorkingDaysVariableName())) {
      formula =
          formula.replace(
              hrConfig.getTotalWorkingDaysVariableName(),
              String.valueOf(
                  employeeService.getDaysWorksInPeriod(
                      employee, period.getFromDate(), period.getToDate())));
    }

    // For checking that formula contains variables like $*$
    if (formula.matches("(\\$\\w+\\$).+")) {
      throw new AxelorException(
          TraceBackRepository.CATEGORY_MISSING_FIELD,
          I18n.get(IExceptionMessage.HR_CONFIG_FORMULA_VARIABLE_MISSING),
          hrConfig.getCompany().getName());
    }
    return formula;
  }
}
