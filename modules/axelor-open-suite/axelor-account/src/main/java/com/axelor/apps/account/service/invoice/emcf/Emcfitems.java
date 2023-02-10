package com.axelor.apps.account.service.invoice.emcf;

import java.math.BigDecimal;

public class Emcfitems {

  String name = "";
  Integer price = 0;
  BigDecimal quantity;
  String taxGroup;

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setQuantity(BigDecimal quantity) {
    this.quantity = quantity;
  }

  public void setTaxGroup(String taxGroup) {
    this.taxGroup = taxGroup;
  }
}
