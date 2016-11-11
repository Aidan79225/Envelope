package com.aidan.aidanenvelopesavemoney.HistoryList;

import android.content.Context;
import android.widget.Adapter;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.MonthHistoryDAO;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.List;

/**
 * Created by s352431 on 2016/11/8.
 */
public class HistoryMonthPresenter implements HistoryMonthContract.presenter {
    HistoryMonthContract.view view;
    HistoryMonthAdapter adapter;

    public HistoryMonthPresenter(HistoryMonthContract.view view) {
        this.view = view;
    }

    @Override
    public void start() {
        view.findView();
        view.setListView();
    }

    @Override
    public Adapter getAdapter(Context context) {
        if (adapter == null) {
            adapter = new HistoryMonthAdapter(context);
        }
        return adapter;
    }

    @Override
    public List<Account> findEnvelopsAccount(Envelope envelope) {
        List<Account> accounts = AccountDAO.getInstance().getEnvelopsAccount(envelope.getId(), MonthHistoryDAO.accountTableName);
//        String id = envelope.getId();
//        for(Account account : LoadDataSingleton.getInstance().getHistoryAccountList()){
//            if (account.getEnvelopId().equals(id))
//                accounts.add(account);
//        }
        return accounts;
    }

}
