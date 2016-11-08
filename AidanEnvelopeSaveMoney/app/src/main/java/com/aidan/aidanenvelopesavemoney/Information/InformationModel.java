package com.aidan.aidanenvelopesavemoney.Information;

import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.List;

/**
 * Created by s352431 on 2016/10/20.
 */
public class InformationModel {
    private List<Envelope> envelopeList;
    private List<Account> accountList;

    public InformationModel() {
        envelopeList = LoadDataSingleton.getInstance().getEnvelopeList();
        accountList = LoadDataSingleton.getInstance().getAccountList();
    }

    public List<Envelope> getEnvelopeList() {
        return envelopeList;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

}
