package com.aidan.aidanenvelopesavemoney.AccountDetail;

import com.aidan.aidanenvelopesavemoney.Model.Account;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountDetailPresenter implements AccountDetailContract.presenter {
    AccountDetailContract.view view;
    AccountDetailModel model;
    Account account;
    boolean change = true;

    public AccountDetailPresenter(AccountDetailContract.view view) {
        this.view = view;
        model = new AccountDetailModel();
    }

    @Override
    public void start() {
        view.findView();
        view.setView(account);
        view.setViewClick();
    }

    @Override
    public void setData(Account account) {
        this.account = account;
    }

    @Override
    public void changeButtonClick() {
        if (change) {
            view.prepareChange();
            change = false;
        } else {
            view.saveChange();
            change = true;
        }
    }

    @Override
    public void setAccount(String cost, String comment) {
        account.setCost(Integer.valueOf(cost));
        account.setComment(comment);
        model.setAccountChange(account);
        view.setView(account);
    }
}
