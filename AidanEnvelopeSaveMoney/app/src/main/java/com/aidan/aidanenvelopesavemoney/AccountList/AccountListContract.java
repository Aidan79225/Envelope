package com.aidan.aidanenvelopesavemoney.AccountList;

/**
 * Created by Aidan on 2016/10/4.
 */

public interface AccountListContract {
    interface view {
        void findView();

        void setAccountListView();

    }

    interface presenter {
        void start();


        void adapterLoadData(AccountListAdapter adapter);

        void deleteAccount(int position);
        void destroy();
    }

}
