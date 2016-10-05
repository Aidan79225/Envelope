package com.aidan.aidanenvelopesavemoney.DataBase;

import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Aidan on 2016/10/3.
 */

public class LoadDataSingleton {
    private List<Envelope> envelopeList = new ArrayList<>();
    private HashMap<String,Envelope> envelopeHashMap = new HashMap<>();
    private List<Account> accountList = new ArrayList<>();
    private static  LoadDataSingleton loadDataSingleton;
    public static LoadDataSingleton getInstance(){
        if(loadDataSingleton == null )
            loadDataSingleton = new LoadDataSingleton();
        return loadDataSingleton;
    }

    public List<Envelope> getEnvelopeList() {
        return envelopeList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }
    public void addAccount(Account account){
        accountList.add(account);
    }

    public void saveToDB() {
        try {
            EnvelopeDAO.getInstance().removeAll();
            for (Envelope envelope : envelopeList) {
                if (!EnvelopeDAO.getInstance().update(envelope))
                    EnvelopeDAO.getInstance().insert(envelope);
                for (Account account : envelope.getAccountList()) {
                    if (!AccountDAO.getInstance().update(account))
                        AccountDAO.getInstance().insert(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Envelope getEnvelope(String id){
        return envelopeHashMap.get(id);
    }
    public void loadFromDB() {
        try {
            envelopeList = EnvelopeDAO.getInstance().getAll();
            accountList = AccountDAO.getInstance().getAll();
            for (Envelope envelope : envelopeList) {
//                envelope.setAccountList(AccountDAO.getInstance().getEnvelopsAccount(envelope.getId()));
                envelopeHashMap.put(envelope.getId(),envelope);
                for(Account account : accountList){
                    if(account.getEnvelopId().equals(envelope.getId()))
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
