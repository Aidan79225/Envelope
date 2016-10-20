package com.aidan.aidanenvelopesavemoney.AccountList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountListAdapter extends BaseAdapter {
    List<Account> accountList = new ArrayList<>();
    private Activity context;

    public AccountListAdapter(Activity context) {
        this.context = context;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;

    }

    @Override
    public int getCount() {
        return accountList.size() + 1;
    }

    @Override
    public Account getItem(int i) {
        return accountList.get(i - 1);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_account, viewGroup, false);
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            viewHolder.commentTextView = (TextView) view.findViewById(R.id.commentTextView);
            viewHolder.costTextView = (TextView) view.findViewById(R.id.costTextView);
            viewHolder.timeTextView = (TextView) view.findViewById(R.id.timeTextView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (i == 0) {
            viewHolder.nameTextView.setText(R.string.envelop_name);
            viewHolder.commentTextView.setText(R.string.comment_name);
            viewHolder.costTextView.setText(R.string.cost_number);
            viewHolder.timeTextView.setText(R.string.date);
        } else {
            Account account = getItem(i);
            viewHolder.nameTextView.setText(account.getEnvelopeName());
            viewHolder.commentTextView.setText(account.getComment());
            viewHolder.costTextView.setText(account.getCost() + "");
            viewHolder.timeTextView.setText(account.getDate());
        }
        return view;
    }

    class ViewHolder {
        public TextView nameTextView;
        public TextView commentTextView;
        public TextView costTextView;
        public TextView timeTextView;
    }
}
