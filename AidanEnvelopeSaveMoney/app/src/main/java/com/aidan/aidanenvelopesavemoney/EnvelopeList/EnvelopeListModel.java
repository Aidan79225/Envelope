package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import android.util.Log;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.DBHelper;
import com.aidan.aidanenvelopesavemoney.DataBase.EnvelopeDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 2016/10/2.
 */

public class EnvelopeListModel {
    private static final String TAG = "EnvelopeListModel";
    private List<Envelope> envelopeList = new ArrayList<>();
    private List<Account> accountList = new ArrayList<>();
    private List<EnvelopeListContract.newData> updateList = new ArrayList<>();

    public void addUpdateList(EnvelopeListContract.newData newData) {
        updateList.add(newData);
    }

    public void addEnvelope(Envelope envelope) {
        envelopeList.add(envelope);
        for (EnvelopeListContract.newData newData : updateList)
            newData.update();
        LoadDataSingleton.getInstance().saveEnvelope(envelope);
    }

    public List<Envelope> getEnvelopeList() {
        return envelopeList;
    }

    public void saveToDB() {
        LoadDataSingleton.getInstance().saveToDB();
    }

    public void loadFromDB() {
        envelopeList = LoadDataSingleton.getInstance().getEnvelopeList();
        accountList = LoadDataSingleton.getInstance().getAccountList();
    }
}
