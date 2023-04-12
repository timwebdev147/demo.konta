package com.axelor.apps.account.service.invoice.emcf;

import com.axelor.apps.account.db.Invoice;
import com.axelor.apps.account.db.InvoiceLine;
import com.axelor.apps.base.db.Company;
import com.axelor.auth.AuthUtils;
import com.axelor.db.JPA;
import com.google.gson.Gson;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wslite.json.JSONException;
import wslite.json.JSONObject;

public class EmcfCore {

  Company user;
  Invoice invoice;
  OkHttpClient emcfClient = new OkHttpClient().newBuilder().build();

  int if_aib = 0;

  private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  // private JsonGenerator Jgen;

  public EmcfCore(Company user, Invoice invoice, OkHttpClient emcfClient) {
    this.user = user;
    this.invoice = invoice;
    this.emcfClient = emcfClient;
  }

  public String emcfdataGson() {
    EmcfData emcfDataGson = new EmcfData();

    List<EmcfPayment> payments = new LinkedList<>();
    payments.add(this.emcfPaymentType());

    emcfDataGson.setIfu(emcfUserIfu());
    emcfDataGson.setType(this.emcfInvoiceTypeEnum());
    emcfDataGson.setItems(this.emcfItemsProducts());

    /*if (if_aib > 0) {
      emcfDataGson.setAib(emcfAib());
    }*/

    // emcfDataGson.setAib(emcfAib());
    emcfDataGson.setClient(emcfClient());
    emcfDataGson.setOperator(emcfOperator());
    emcfDataGson.setPayment(payments);

    // Dis ce qui se passe ici en regardant dans la documentation
    if (this.emcfInvoiceTypeEnum().equals("FA") || this.emcfInvoiceTypeEnum().equals("EA")) {
      try {
        emcfDataGson.setReference(this.emcfReference());
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }

    Gson gson = new Gson();
    String dataJson = null;
    dataJson = gson.toJson(emcfDataGson);
    return dataJson;
  }

  public String emcfStatus(String emcfUrl, String emcfToken) throws JSONException {
    String emcfstatus = null;
    Request request =
        new Request.Builder()
            .url(emcfUrl)
            .method("GET", null)
            .addHeader("Authorization", "Bearer " + emcfToken + "")
            .build();

    try {
      Response response = this.emcfClient.newCall(request).execute();
      emcfstatus = response.body().string();
      log.debug(emcfstatus);
    } catch (IOException e) {
      e.printStackTrace();
    }

    JSONObject object = new JSONObject(emcfstatus);
    try {
      if (object.getBoolean("status")) {
        emcfstatus = "true";
      } else {
        emcfstatus = "false";
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    log.debug(emcfstatus);
    return emcfstatus;
  }

  public String emcfAction(String emcfUrl, String emcfToken, String emcfId, String emcfaction) {
    String emcfResponse = null;
    MediaType mediaType = MediaType.parse("text/plain");
    RequestBody body = RequestBody.create(mediaType, "");
    Request request =
        new Request.Builder()
            .url(emcfUrl + "/" + emcfId + "/" + emcfaction)
            .method("PUT", body)
            .addHeader("Authorization", "Bearer " + emcfToken + "")
            .build();
    try {
      Response response = this.emcfClient.newCall(request).execute();
      emcfResponse = response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
    log.debug(emcfResponse);
    return emcfResponse;
  }

  // TODO : Implementer la fonction emcfCheck pour : DEMANDE DE DÉTAILS SUR LA FACTURE EN ATTENTE
  // public String emcfCheck() {}

  private String emcfUserIfu() {
    String userifu;
    user = AuthUtils.getUser().getActiveCompany();
    userifu = user.getIfu();
    return userifu;
  }

  public String emcfUserToken() {
    String userToken;
    user = AuthUtils.getUser().getActiveCompany();
    userToken = user.getEmcfToken();
    log.debug("=============+++++++++++++++++=============" + userToken);
    return userToken;
  }

  private String emcfAib() {
    String aib = null;
    if (!invoice.getCompany().getIfu().isEmpty()) {
      aib = "A";
    } else {
      aib = "B";
    }
    return aib;
  }

  /**
   * Type de la facture : - FV – Facture de vente - EV – Facture de vente à l’exportation - FA –
   * Facture d’avoir - EA – Facture d’avoir à l’exportation
   *
   * @return
   */
  private String emcfInvoiceTypeEnum() {
    String invoiceTypeEnum;
    int type = this.invoice.getOperationTypeSelect();
    log.debug(
        "TYPE DE FACTURE : ==============================================================================================");
    log.debug(String.valueOf(this.invoice.getOperationTypeSelect()));
    log.debug(
        "==============================================================================================");
    switch (type) {
        /*case 1:
          invoiceTypeEnum = "FV";
          break;
        case 2:
          invoiceTypeEnum = "EA";
          break;*/
      case 3:
        invoiceTypeEnum = "FV";
        break;
      case 4:
        invoiceTypeEnum = "FA";
        break;
      default:
        invoiceTypeEnum = "FV";
    }
    // log.debug(invoiceTypeEnum);
    return invoiceTypeEnum;
  }

  /**
   * Liste des produits d'une facture
   *
   * @return
   */
  private ArrayList<Emcfitems> emcfItemsProducts() {

    // Emcfitems items = new Emcfitems();
    List<InvoiceLine> invoiceLines = this.invoice.getInvoiceLineList();

    log.debug(
        "VOIR LE TABLEAU DES PRODUITS DANS INVOICELINES =========================================================================");
    log.debug(String.valueOf(invoiceLines));
    log.debug(
        "==============================================================================================");

    ArrayList<Emcfitems> emcfitems = new ArrayList<>();
    for (InvoiceLine invoiceLine : invoiceLines) {

      Emcfitems items = new Emcfitems();
      items.setName(invoiceLine.getProductName());

      BigDecimal qty, a;
      qty = invoiceLine.getInTaxTotal();

      /*if (invoiceLine.getInvoice().getCompany().getTaxRegime().equals("TPS")
          || invoiceLine.getProduct().getTaxGroup().equals("A")
          || invoiceLine.getProduct().getTaxGroup().equals("C")) {
        items.setPrice(invoiceLine.getPrice().intValue());
        items.setQuantity(invoiceLine.getQty());
      } else {
        items.setPrice(invoiceLine.getInTaxPrice().setScale(0, RoundingMode.HALF_UP).intValue());
        a = invoiceLine.getInTaxPrice().setScale(0, RoundingMode.HALF_UP);
        qty = invoiceLine.getInTaxTotal().divide(a, 2, RoundingMode.HALF_UP);
        items.setQuantity(qty);
        log.debug("++++++++++++++++ " + a);
        log.debug("++++++++++++++++ " + qty);
      }*/

      if (invoiceLine.getInvoice().getCompany().getTaxRegime().equals("TPS")
          || invoiceLine.getProduct().getTaxGroup().equals("A")
          || invoiceLine.getProduct().getTaxGroup().equals("C")) {
        items.setPrice(invoiceLine.getPrice().intValue());
      } else {
        items.setPrice(invoiceLine.getInTaxPrice().intValue());
      }
      log.debug(
          "QQQ===================="
              + invoiceLine.getInTaxPrice().setScale(0, RoundingMode.HALF_UP)
              + "========================="
              + invoiceLine.getPrice().setScale(0, RoundingMode.HALF_UP));

      if (invoiceLine.getProduct().getProductTypeSelect().equals("service")) {
        if_aib += 1;
      }

      items.setTaxGroup(invoiceLine.getProduct().getTaxGroup());
      emcfitems.add(items);
    }

    log.debug(
        "VOIR LE TABLEAU DES PRODUITS  =========================================================================");
    log.debug(String.valueOf(emcfitems));
    log.debug(
        "==============================================================================================");
    return emcfitems;
  }

  private EmcfClient emcfClient() {
    EmcfClient client = new EmcfClient();
    client.setIfu(this.invoice.getPartner().getIfu());
    client.setName(this.invoice.getPartner().getName()); // Nom du client
    log.debug("?.>>>>>>>>>>>>>>>>>>>>>     " + this.invoice.getPartner().getName());
    client.setContact(this.invoice.getPartner().getMobilePhone()); // Contact du client
    client.setAddress(
        this.invoice.getPartner().getEmailAddress().getAddress()); // Adresse mail du client
    // log.debug(String.valueOf(client));
    return client;
  }

  private Emcfoperator emcfOperator() {
    Emcfoperator operatorInfo = new Emcfoperator();
    operatorInfo.setId(this.invoice.getCreatedBy().getId().toString());
    operatorInfo.setName(this.invoice.getCreatedBy().getName());
    // log.debug(String.valueOf(operatorInfo));
    return operatorInfo;
  }

  private EmcfPayment emcfPaymentType() {
    EmcfPayment typePayement = new EmcfPayment();
    String paymentMode = this.invoice.getPaymentMode().getName();

    if (paymentMode.equals("ENC-Especes") || paymentMode.equals("DEC-Especes")) {
      log.debug("================ " + paymentMode + "================");
      typePayement.setName("ESPECES");
    } else if (paymentMode.equals("ENC-Chèque") || paymentMode.equals("DEC-Chèque")) {
      log.debug("================ " + paymentMode + "================");
      typePayement.setName("CHEQUES");
    } else {
      log.debug("================ " + paymentMode + "================");
      typePayement.setName("VIREMENT");
    }

    typePayement.setAmount(this.invoice.getAmountPaid().intValue());
    return typePayement;
  }

  /**
   * Référence de la facture originale en cas de facture d’avoir (FA ou EA) Le contenu est de 24
   * caractères de Code MECeF/DGI de la facture originale Non optionnel pour les types de factures
   * FA, EA Exemple : 1DER-PKSK-UIHO-UFDH-MVCZ-TRAB
   *
   * @return
   */
  public String emcfReference() throws JSONException {
    String invoiceIdRequest =
        String.format(
            "select t.id FROM Invoice t WHERE t.company = %s AND t.invoiceId = '%s'",
            AuthUtils.getUser().getActiveCompany().getId(),
            this.invoice.getInternalReference().toUpperCase());
    log.debug(invoiceIdRequest);
    Query query = JPA.em().createQuery(invoiceIdRequest);
    List invoiceId = query.getResultList();
    log.debug(String.valueOf(invoiceId));
    log.debug(String.valueOf(invoiceId.size()));

    String codeMECeFDGIRequest =
        String.format(
            "SELECT t.confirmResponse FROM InvoiceEmcf as t WHERE  t.standardizedInvoice = %s ",
            invoiceId.iterator().next());
    Query query1 = JPA.em().createQuery(codeMECeFDGIRequest);
    String codeMECeFDGI = (String) query1.getSingleResult();
    log.debug(codeMECeFDGI);

    JSONObject codeMECeFDGIobject = new JSONObject(codeMECeFDGI);
    try {
      codeMECeFDGI = codeMECeFDGIobject.getString("codeMECeFDGI");
      log.debug(codeMECeFDGI);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return codeMECeFDGI;
  }
}
