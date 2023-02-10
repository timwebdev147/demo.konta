package com.axelor.apps.account.service.invoice.emcf;

public class EmcfCsv {

  String dateTime;
  String qrCode;
  String codeMECeFDGI;
  String counters;
  String nim;

  public EmcfCsv(String dateTime, String qrCode, String codeMECeFDGI, String counters, String nim) {
    this.dateTime = dateTime;
    this.qrCode = qrCode;
    this.codeMECeFDGI = codeMECeFDGI;
    this.counters = counters;
    this.nim = nim;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  public String getQrCode() {
    return qrCode;
  }

  public void setQrCode(String qrCode) {
    this.qrCode = qrCode;
  }

  public String getCodeMECeFDGI() {
    return codeMECeFDGI;
  }

  public void setCodeMECeFDGI(String codeMECeFDGI) {
    this.codeMECeFDGI = codeMECeFDGI;
  }

  public String getCounters() {
    return counters;
  }

  public void setCounters(String counters) {
    this.counters = counters;
  }

  public String getNim() {
    return nim;
  }

  public void setNim(String nim) {
    this.nim = nim;
  }

  public String toString() {
    return "Book [dataTime="
        + dateTime
        + ", qrCode="
        + qrCode
        + ", codeMECeFDGI="
        + codeMECeFDGI
        + ", counters="
        + counters
        + ", nim="
        + nim
        + "]";
  }
}
