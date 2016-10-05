package com.aidan.aidanenvelopesavemoney.AccountList;

import android.widget.BaseAdapter;

/**
 * Created by Aidan on 2016/10/4.
 */

public interface AccountListContract {
    interface view{
        void findView();
        void setAccountListView();

    }
    interface presenter{
        void start();
        void setAdapter(AccountListAdapter adapter);
        void adapterLoadData();
    }

}
