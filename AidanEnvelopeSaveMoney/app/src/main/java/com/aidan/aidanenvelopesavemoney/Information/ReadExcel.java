package com.aidan.aidanenvelopesavemoney.Information;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.EnvelopeDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.DataBase.MonthHistoryDAO;
import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;
import com.aidan.aidanenvelopesavemoney.Model.MonthHistory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

/**
 * Created by s352431 on 2016/10/20.
 */
public class ReadExcel {
    public void read(String inputFile) {
        File inputWorkbook = new File(inputFile);
        Workbook w;
        try {
            w = Workbook.getWorkbook(inputWorkbook);
            loadEnvelopes(w);
            loadAccounts(w);
            loadHistoryEnvelopes(w);
            loadHistoryAccounts(w);
            loadHistoryMonth(w);
            LoadDataSingleton.getInstance().loadFromDB();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public void loadEnvelopes(Workbook w) {
        Sheet sheet = w.getSheet(0);
        List<Envelope> envelopeList = new ArrayList<>();
        for (int i = 0; i < sheet.getRows(); i++) {
            String temp = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                temp += "," + cell.getContents();
            }
            Singleton.log(temp);
            if (i == 0) continue;
            Envelope envelope = new Envelope();
            envelope.setName(sheet.getCell(0, i).getContents());
            envelope.setMax(Integer.valueOf(sheet.getCell(1, i).getContents()));
            envelope.setCost(Integer.valueOf(sheet.getCell(2, i).getContents()));
            envelope.setId(sheet.getCell(3, i).getContents());
            envelopeList.add(envelope);
        }
        LoadDataSingleton.getInstance().getEnvelopeList().clear();
        EnvelopeDAO.getInstance().removeAll();
        for (Envelope envelope : envelopeList) {
            LoadDataSingleton.getInstance().saveEnvelope(envelope);
        }

    }

    public void loadAccounts(Workbook w) {
        Sheet sheet = w.getSheet(1);
        List<Account> accountList = new ArrayList<>();
        for (int i = sheet.getRows() - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                temp += "," + cell.getContents();
            }
            Singleton.log(temp);
            if (i == 0) continue;
            Account account = new Account();
            account.setComment(sheet.getCell(0, i).getContents());
            account.setCost(Integer.valueOf(sheet.getCell(1, i).getContents()));
            account.setTime(Long.valueOf(sheet.getCell(6, i).getContents()));
            account.setId(sheet.getCell(3, i).getContents());
            account.setEnvelopeName(sheet.getCell(4, i).getContents());
            account.setEnvelopId(sheet.getCell(5, i).getContents());
            accountList.add(account);
        }
        LoadDataSingleton.getInstance().getAccountList().clear();
        AccountDAO.getInstance().removeAll();
        for (Account account : accountList) {
            LoadDataSingleton.getInstance().saveAccount(account);
        }
    }

    public void loadHistoryEnvelopes(Workbook w) {
        if (w.getSheets().length <= 2) return;
        Sheet sheet = w.getSheet(2);
        List<Envelope> envelopeList = new ArrayList<>();
        for (int i = 0; i < sheet.getRows(); i++) {
            String temp = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                temp += "," + cell.getContents();
            }
            Singleton.log(temp);
            if (i == 0) continue;
            Envelope envelope = new Envelope();
            envelope.setName(sheet.getCell(0, i).getContents());
            envelope.setMax(Integer.valueOf(sheet.getCell(1, i).getContents()));
            envelope.setCost(Integer.valueOf(sheet.getCell(2, i).getContents()));
            envelope.setId(sheet.getCell(3, i).getContents());
            envelopeList.add(envelope);
        }
        LoadDataSingleton.getInstance().getHistoryEnvelopeList().clear();
        EnvelopeDAO.getInstance().removeAll(MonthHistoryDAO.envelopeTableName);
        for (Envelope envelope : envelopeList) {
            LoadDataSingleton.getInstance().saveEnvelope(envelope, MonthHistoryDAO.envelopeTableName);
        }
    }

    public void loadHistoryAccounts(Workbook w) {
        if (w.getSheets().length <= 2) return;
        Sheet sheet = w.getSheet(3);
        List<Account> accountList = new ArrayList<>();
        for (int i = sheet.getRows() - 1; i >= 0; i--) {
            String temp = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                temp += "," + cell.getContents();
            }
            Singleton.log(temp);
            if (i == 0) continue;
            Account account = new Account();
            account.setComment(sheet.getCell(0, i).getContents());
            account.setCost(Integer.valueOf(sheet.getCell(1, i).getContents()));
            account.setTime(Long.valueOf(sheet.getCell(6, i).getContents()));
            account.setId(sheet.getCell(3, i).getContents());
            account.setEnvelopeName(sheet.getCell(4, i).getContents());
            account.setEnvelopId(sheet.getCell(5, i).getContents());
            accountList.add(account);
        }
        LoadDataSingleton.getInstance().getHistoryAccountList().clear();
        AccountDAO.getInstance().removeAll(MonthHistoryDAO.accountTableName);
        for (Account account : accountList) {
            LoadDataSingleton.getInstance().saveAccount(account, MonthHistoryDAO.accountTableName);
        }
    }

    public void loadHistoryMonth(Workbook w) {
        if (w.getSheets().length <= 2) return;
        Sheet sheet = w.getSheet(4);
        List<MonthHistory> monthHistories = new ArrayList<>();
        for (int i = 0; i < sheet.getRows(); i++) {
            String temp = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                temp += "," + cell.getContents();
            }
            Singleton.log(temp);
            if (i == 0) continue;
            MonthHistory monthHistory = new MonthHistory();
            monthHistory.setName(sheet.getCell(0, i).getContents());
            monthHistory.setEnvelopId(sheet.getCell(1, i).getContents());
            monthHistory.setId(sheet.getCell(2, i).getContents());
            monthHistories.add(monthHistory);
        }
        LoadDataSingleton.getInstance().getMonthHistoryList().clear();
        MonthHistoryDAO.getInstance().removeAll();
        for (MonthHistory monthHistory : monthHistories) {
            LoadDataSingleton.getInstance().saveMonth(monthHistory);
        }
    }

}
