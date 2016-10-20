package com.aidan.aidanenvelopesavemoney.AccountDetail;

import com.aidan.aidanenvelopesavemoney.Model.Account;

/**
 * Created by Aidan on 2016/10/4.
 */

public interface AccountDetailContract {
    interface view {
        void findView();

        void setView(Account account);

        void setViewClick();

        void prepareChange();

        void saveChange();
    }

    interface presenter {
        void start();

        void setData(Account account);

        void changeButtonClick();

        void setAccount(String cost, String comment);
    }
}
