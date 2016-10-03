package com.aidan.aidanenvelopesavemoney.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Aidan on 2016/10/2.
 */

public class Envelope {
    private String id = "";
    private String name ="";
    private float max = 0;
    private float cost = 0;
    private long index =0;
    private List<Account> accountList = new ArrayList<Account>();
    public Envelope(){
        id = UUID.randomUUID().toString().substring(0,10);
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public void addAccount(Account account) {
        accountList.add(account);
        cost += account.getCost();

    }
    public void addAccountFromDB(Account account) {
        accountList.add(account);
    }





}
