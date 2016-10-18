package com.aidan.aidanenvelopesavemoney.AccountList;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountListModel {
    Envelope envelope;
    List<Account> accountList = new ArrayList<>();
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
    public void deleteAccount(int position){
        long id = accountList.get(position-1).getIndex();
        accountList.remove(position-1);
        AccountDAO.getInstance().delete(id);

    }




}
