package com.aidan.aidanenvelopesavemoney.AccountDetail;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountDetailPresenter implements AccountDetailContract.presenter {
    AccountDetailContract.view view;
    AccountDetailModel model;
    public AccountDetailPresenter(AccountDetailContract.view view){
        this.view = view;
        model = new AccountDetailModel();
    }
    @Override
    public void start() {
        view.findView();
    }
}
