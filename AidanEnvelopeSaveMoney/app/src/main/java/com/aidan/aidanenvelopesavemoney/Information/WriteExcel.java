package com.aidan.aidanenvelopesavemoney.Information;

import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.io.File;
import java.io.IOException;
import java.util.List;

import jxl.Workbook;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by s352431 on 2016/10/20.
 */
public class WriteExcel {
    public static final String fileName = "LVBEnvelopeSaveMoney";
    private InformationModel model;

    public WriteExcel(InformationModel model) {
        this.model = model;
    }


    public void createExcel(String path) {
        File file;
        File dir = new File(path);
        file = new File(dir, fileName + ".xls");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        writeExcel(file);

    }

    private void writeExcel(File file) {
        try {
            WritableWorkbook wwb;
//            OutputStream os = new FileOutputStream(file);
            wwb = Workbook.createWorkbook(file);
            writeEnvelope(wwb);
            writeAccount(wwb);
            writeHistoryEnvelope(wwb);
            writeHistoryAccount(wwb);
            wwb.write();
            wwb.close();
        } catch (WriteException writeException) {
            writeException.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    private void writeEnvelope(WritableWorkbook wwb) {
        try {

            WritableSheet sheet = wwb.createSheet("信封袋", 0);
            String[] title = {"信封名稱", "信封上限", "信封花費", "信封袋Id"};
            Label label;
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            List<Envelope> envelopeList = model.getEnvelopeList();
            Envelope envelope;
            for (int i = 0; i < envelopeList.size(); i++) {
                envelope = envelopeList.get(i);
                Label envelopeName = new Label(0, i + 1, envelope.getName());
                Label envelopeMax = new Label(1, i + 1, envelope.getMax() + "");
                Label envelopeCost = new Label(2, i + 1, envelope.getCost() + "");
                Label envelopeId = new Label(3, i + 1, envelope.getId());
                sheet.addCell(envelopeName);
                sheet.addCell(envelopeMax);
                sheet.addCell(envelopeCost);
                sheet.addCell(envelopeId);
            }
        } catch (WriteException writeException) {
            writeException.printStackTrace();
        }
    }

    private void writeAccount(WritableWorkbook wwb) {
        try {
            WritableSheet sheet = wwb.createSheet("帳務", 1);
            String[] title = {"帳務名稱", "帳務花費", "帳務日期", "帳務Id", "信封名稱", "信封Id", "帳務時間"};
            Label label;
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            List<Account> accountList = model.getAccountList();
            Account account;
            for (int i = 0; i < accountList.size(); i++) {
                account = accountList.get(i);
                Label accountName = new Label(0, i + 1, account.getComment());
                Label accountCost = new Label(1, i + 1, account.getCost() + "");
                Label accountDate = new Label(2, i + 1, account.getDate() + "");
                Label accountId = new Label(3, i + 1, account.getId());
                Label envelopeName = new Label(4, i + 1, account.getEnvelopeName());
                Label envelopeId = new Label(5, i + 1, account.getEnvelopId());
                Label accountTime = new Label(6, i + 1, account.getTime() + "");

                sheet.addCell(accountName);
                sheet.addCell(accountCost);
                sheet.addCell(accountDate);
                sheet.addCell(accountId);
                sheet.addCell(envelopeName);
                sheet.addCell(envelopeId);
                sheet.addCell(accountTime);
            }
        } catch (WriteException writeException) {
            writeException.printStackTrace();
        }
    }
    private void writeHistoryEnvelope(WritableWorkbook wwb) {
        try {

            WritableSheet sheet = wwb.createSheet("歷史信封袋", 2);
            String[] title = {"信封名稱", "信封上限", "信封花費", "信封袋Id"};
            Label label;
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            List<Envelope> envelopeList = model.getEnvelopeList();
            Envelope envelope;
            for (int i = 0; i < envelopeList.size(); i++) {
                envelope = envelopeList.get(i);
                Label envelopeName = new Label(0, i + 1, envelope.getName());
                Label envelopeMax = new Label(1, i + 1, envelope.getMax() + "");
                Label envelopeCost = new Label(2, i + 1, envelope.getCost() + "");
                Label envelopeId = new Label(3, i + 1, envelope.getId());
                sheet.addCell(envelopeName);
                sheet.addCell(envelopeMax);
                sheet.addCell(envelopeCost);
                sheet.addCell(envelopeId);
            }
        } catch (WriteException writeException) {
            writeException.printStackTrace();
        }
    }
    private void writeHistoryAccount(WritableWorkbook wwb) {
        try {
            WritableSheet sheet = wwb.createSheet("歷史帳務", 3);
            String[] title = {"帳務名稱", "帳務花費", "帳務日期", "帳務Id", "信封名稱", "信封Id", "帳務時間"};
            Label label;
            for (int i = 0; i < title.length; i++) {
                label = new Label(i, 0, title[i]);
                sheet.addCell(label);
            }
            List<Account> accountList = model.getAccountList();
            Account account;
            for (int i = 0; i < accountList.size(); i++) {
                account = accountList.get(i);
                Label accountName = new Label(0, i + 1, account.getComment());
                Label accountCost = new Label(1, i + 1, account.getCost() + "");
                Label accountDate = new Label(2, i + 1, account.getDate() + "");
                Label accountId = new Label(3, i + 1, account.getId());
                Label envelopeName = new Label(4, i + 1, account.getEnvelopeName());
                Label envelopeId = new Label(5, i + 1, account.getEnvelopId());
                Label accountTime = new Label(6, i + 1, account.getTime() + "");

                sheet.addCell(accountName);
                sheet.addCell(accountCost);
                sheet.addCell(accountDate);
                sheet.addCell(accountId);
                sheet.addCell(envelopeName);
                sheet.addCell(envelopeId);
                sheet.addCell(accountTime);
            }
        } catch (WriteException writeException) {
            writeException.printStackTrace();
        }
    }


}
