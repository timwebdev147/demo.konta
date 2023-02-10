package com.axelor.apps.account.service.invoice.emcf;

import com.axelor.app.AppSettings;
import com.axelor.apps.account.db.Invoice;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.EnumMap;
import java.util.Map;
import javax.imageio.ImageIO;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wslite.json.JSONException;
import wslite.json.JSONObject;

public class EmcfInvoice {

  private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
  String confirmInvoice = null;
  String confirmResponse = null;
  Gson gson = new Gson();

  EmcfCore emcfCore;
  AppSettings appSettings = AppSettings.get();
  String emcfUrl = appSettings.get("emcf.url.dev");
  // String emcfToken =
  // "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1bmlxdWVfbmFtZSI6IjAyMDE5MTA1ODMzMDN8VFMwMTAwMDI1NSIsInJvbGUiOiJUYXhwYXllciIsIm5iZiI6MTYxODkxNTA5MSwiZXhwIjoxNjM0Njg0NDAwLCJpYXQiOjE2MTg5MTUwOTEsImlzcyI6ImltcG90cy5iaiIsImF1ZCI6ImltcG90cy5iaiJ9.XHoX1_SMZ-rEjbBnK-ASxzG2MJr9ELatsEwx0semf_o";
  String emcfId = null;
  String submitInvoice = null;

  public EmcfInvoice(EmcfCore emcfCore) {
    this.emcfCore = emcfCore;
  }

  public String emcfSubmit(String emcfToken) throws JSONException {
    String emcfdataSubmit = this.emcfCore.emcfdataGson();
    log.debug(emcfdataSubmit);
    if (emcfCore.emcfStatus(emcfUrl, emcfToken).equals("true")) {
      OkHttpClient submitClient = new OkHttpClient();
      MediaType mediaType = MediaType.parse("application/json");
      RequestBody body = RequestBody.create(mediaType, emcfdataSubmit);
      Request request =
          new Request.Builder()
              .url(emcfUrl)
              .method("POST", body)
              .addHeader("Authorization", "Bearer " + emcfToken + "")
              .addHeader("Content-Type", "application/json")
              .build();
      try {
        Response response = submitClient.newCall(request).execute();
        submitInvoice = response.body().string();
        log.debug(submitInvoice);
      } catch (IOException e) {
        e.printStackTrace();
      }

      try {
        JSONObject object = new JSONObject(submitInvoice);
        emcfId = object.getString("uid");
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
    log.debug(emcfId);
    return emcfId;
  }

  public String emcfConfirm(String emcfToken) throws JSONException {
    String emcfaction = "confirm";
    // String dateTime = null, qrCode = null, codeMECeFDGI = null, counters, nim = null;
    JSONObject object = null;
    if (emcfCore.emcfStatus(emcfUrl, emcfToken).equals("true")) {
      confirmResponse = emcfCore.emcfAction(emcfUrl, emcfToken, emcfId, emcfaction);
      log.debug(confirmResponse);

      /*confirmInvoice = gson.toJson(confirmResponse);*/
      // invoice.setNote(confirmInvoice);
      /*log.debug(confirmInvoice);*/
    }
    return confirmResponse;
  }

  public void emcfCancel(String emcfToken) throws JSONException {
    String confirmInvoice = null;
    String emcfaction = "annuler";
    // String dateTime = null, qrCode = null, codeMECeFDGI = null, counters, nim = null;
    JSONObject object = null;
    if (emcfCore.emcfStatus(emcfUrl, emcfToken).equals("true")) {
      confirmInvoice = emcfCore.emcfAction(emcfUrl, emcfToken, emcfId, emcfaction);

      try {
        object = new JSONObject(confirmInvoice);
      } catch (JSONException e) {
        e.printStackTrace();
      }
    }
  }
  /*public  void emcfCheck() {
   String checkInvoice = null;
  }*/

  public String getEmcfSubmitInvoice() {
    return submitInvoice;
  }

  public String emcfQrcode(Invoice invoice) throws JSONException {

    String qrCode = null;
    JSONObject object = new JSONObject(confirmResponse);
    // InvoiceRepository invoiceRepository = new InvoiceRepository();

    qrCode = object.getString("qrCode");
    String myCodeText = qrCode;
    File qrCodeDir = new File("emcfQrCodeDir");
    if (!qrCodeDir.exists()) {
      qrCodeDir.mkdirs();
    }
    log.debug(qrCodeDir.getAbsolutePath());
    String qrCodeFilePath = qrCodeDir.getAbsolutePath() + "/" + invoice.getId() + "_qrCode.png";
    File qrCodeFile = new File(qrCodeFilePath);
    String qrCodeAbsPath = qrCodeFile.getAbsolutePath();

    try {
      int size = 140;
      String crunchifyFileType = "png";
      File crunchifyFile = new File(qrCodeAbsPath);
      String oi = crunchifyFile.getPath();

      Map<EncodeHintType, Object> crunchifyHintType =
          new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
      crunchifyHintType.put(EncodeHintType.CHARACTER_SET, "UTF-8");

      //  modifier la marge (taille de la bordure blanche).
      crunchifyHintType.put(EncodeHintType.MARGIN, 1); /* default = 4 */
      Object put = crunchifyHintType.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);

      QRCodeWriter mYQRCodeWriter = new QRCodeWriter(); // throws com.google.zxing.WriterException
      BitMatrix crunchifyBitMatrix =
          mYQRCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size, size, crunchifyHintType);
      int CrunchifyWidth = crunchifyBitMatrix.getWidth();

      // La sous-classe BufferedImage décrit une image
      BufferedImage crunchifyImage =
          new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);

      // Crée un Graphics2D, qui peut être utilisé pour dessiner dans cette BufferedImage.
      crunchifyImage.createGraphics();

      //  Graphics2D
      Graphics2D crunchifyGraphics = (Graphics2D) crunchifyImage.getGraphics();

      crunchifyGraphics.setColor(Color.white);

      // fillRect() remplit le rectangle spécifié. Les bords gauche et droit du rectangle sont
      // situés à x et x + largeur - 1.
      // reperer
      crunchifyGraphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);

      // TODO: Please change this color as per your need
      crunchifyGraphics.setColor(Color.DARK_GRAY);

      for (int i = 0; i < CrunchifyWidth; i++) {
        for (int j = 0; j < CrunchifyWidth; j++) {
          if (crunchifyBitMatrix.get(i, j)) {
            crunchifyGraphics.fillRect(i, j, 1, 1);
          }
        }
      }

      // Localiser qr
      ImageIO.write(crunchifyImage, crunchifyFileType, crunchifyFile);

      System.out.println(
          "\nCode qr generer avec succes. \n" + "verifier qr code ici : " + qrCodeAbsPath);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (WriterException e) {
      System.out.println("\n erreur code qr non générer...\n");
      e.printStackTrace();
    }
    log.debug(qrCode);
    return qrCodeAbsPath;
  }
}
