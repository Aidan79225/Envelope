package com.aidan.aidanenvelopesavemoney.AccountDetail;

import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountDetailModel {
    public void setAccountChange(Account account) {
        Envelope envelope = LoadDataSingleton.getInstance().getEnvelope(account.getEnvelopId());
        if (envelope == null) return;
        envelope.getAccountList().remove(account);
        envelope.getAccountList().add(account);
        envelope.refresh();
        LoadDataSingleton.getInstance().saveAccount(account);
    }
}
