package com.aidan.aidanenvelopesavemoney.AccountList;

import com.aidan.aidanenvelopesavemoney.Model.Account;

import java.util.List;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountListPresenter implements AccountListContract.presenter {
    AccountListContract.view view;
    AccountListModel model;
    AccountListAdapter adapter;

    public AccountListPresenter(AccountListContract.view view, List<Account> accountList) {
        this.view = view;
        model = new AccountListModel();
        model.setAccountList(accountList);
    }

    private AccountListPresenter() {

    }

    @Override
    public void start() {
        view.findView();
        view.setAccountListView();

    }

    @Override
    public void setAdapter(AccountListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void adapterLoadData() {
        adapter.setAccountList(model.getAccountList());
    }

    @Override
    public void deleteAccount(int position) {
        model.deleteAccount(position);
        adapter.notifyDataSetChanged();
    }
}
