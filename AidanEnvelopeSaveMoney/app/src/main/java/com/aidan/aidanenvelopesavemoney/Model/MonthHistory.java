package com.aidan.aidanenvelopesavemoney.Model;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.EnvelopeDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.MonthHistoryDAO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Created by s352431 on 2016/10/28.
 */
public class MonthHistory {

    String name;
    String id;
    long index;



    List<Envelope> envelopeList = new ArrayList<>();
    List<Account> accountList = new ArrayList<>();
    public MonthHistory(){
        Calendar now = Calendar.getInstance();
        id = UUID.randomUUID().toString().substring(0, 10);
        name = "歷史資料" +String.valueOf(now.get(Calendar.YEAR)) + String.valueOf(now.get(Calendar.MONTH) + 1);
    }
    public void loadData(){
        envelopeList = EnvelopeDAO.getInstance().getAll(MonthHistoryDAO.envelopeTableName);

    }
    public List<Envelope> getEnvelopeList(){
        return envelopeList;
    }



}
