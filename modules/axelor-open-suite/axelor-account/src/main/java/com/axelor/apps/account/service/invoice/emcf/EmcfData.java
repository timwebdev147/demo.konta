package com.axelor.apps.account.service.invoice.emcf;

import java.io.Serializable;
import java.util.List;

public class EmcfData implements Serializable {
  String ifu;
  String aib;
  String type;
  List<Emcfitems> items;
  EmcfClient client;
  Emcfoperator operator;
  List<EmcfPayment> payment;
  String reference;

  public void setIfu(String ifu) {
    this.ifu = ifu;
  }

  public void setAib(String aib) {
    this.aib = aib;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setItems(List<Emcfitems> items) {
    this.items = items;
  }

  public void setClient(EmcfClient client) {
    this.client = client;
  }

  public void setOperator(Emcfoperator operator) {
    this.operator = operator;
  }

  public void setPayment(List<EmcfPayment> payment) {
    this.payment = payment;
  }

  public void setReference(String reference) {
    this.reference = reference;
  }
}
