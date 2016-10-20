package com.aidan.aidanenvelopesavemoney.DataBase;

import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aidan on 2016/10/3.
 */

public class LoadDataSingleton {
    private List<Envelope> envelopeList = new ArrayList<>();
    private HashMap<String, Envelope> envelopeHashMap = new HashMap<>();
    private List<Account> accountList = new ArrayList<>();
    private static LoadDataSingleton loadDataSingleton;

    public static LoadDataSingleton getInstance() {
        if (loadDataSingleton == null)
            loadDataSingleton = new LoadDataSingleton();
        return loadDataSingleton;
    }

    public List<Envelope> getEnvelopeList() {
        return envelopeList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addAccount(Account account) {
        accountList.add(0, account);
        saveAccount(account);
    }

    public void saveAccount(Account account) {
        if (!AccountDAO.getInstance().update(account))
            AccountDAO.getInstance().insert(account);
    }

    public void saveEnvelope(Envelope envelope) {
        if (!EnvelopeDAO.getInstance().update(envelope))
            EnvelopeDAO.getInstance().insert(envelope);
    }

    public void deleteEnvelope(Envelope envelope) {
        EnvelopeDAO.getInstance().delete(envelope.getIndex());
    }

    public void deleteAccount(Account account) {
        AccountDAO.getInstance().delete(account.getIndex());
    }

    public void saveToDB() {
        try {
            for (Envelope envelope : envelopeList) {
                saveEnvelope(envelope);
            }
            for (Account account : accountList) {
                saveAccount(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Envelope getEnvelope(String id) {
        return envelopeHashMap.get(id);
    }

    public void loadFromDB() {
        try {
            envelopeList = EnvelopeDAO.getInstance().getAll();
            accountList = AccountDAO.getInstance().getAll();
            Collections.reverse(accountList);
            for (Envelope envelope : envelopeList) {
//                envelope.setAccountList(AccountDAO.getInstance().getEnvelopsAccount(envelope.getId()));
                envelopeHashMap.put(envelope.getId(), envelope);
                for (Account account : accountList) {
                    if (account.getEnvelopId().equals(envelope.getId()))
                        envelope.addAccountFromDB(account);
                }
                envelope.refresh();
                Singleton.log("success");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
