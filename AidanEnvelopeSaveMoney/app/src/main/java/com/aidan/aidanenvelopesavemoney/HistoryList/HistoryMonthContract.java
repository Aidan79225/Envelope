package com.aidan.aidanenvelopesavemoney.HistoryList;

import android.content.Context;
import android.widget.Adapter;

import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.List;

/**
 * Created by s352431 on 2016/11/8.
 */
public interface HistoryMonthContract {
    interface view {
        void findView();

        void setListView();
    }

    interface presenter {
        void start();

        Adapter getAdapter(Context context);

        List<Account> findEnvelopsAccount(Envelope envelope);
    }

}
