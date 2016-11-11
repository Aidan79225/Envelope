package com.aidan.aidanenvelopesavemoney.AccountList;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.aidan.aidanenvelopesavemoney.AccountDetail.AccountDetailFragment;
import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.List;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountListFragment extends DialogFragment implements AccountListContract.view {
    ViewGroup rootView;
    AccountListContract.presenter presenter;
    AccountListAdapter adapter;
    ListView accountListView;
    String title = "";

    public static AccountListFragment newInstance(List<Account> accountList) {
        AccountListFragment fragment = new AccountListFragment();
        fragment.presenter = new AccountListPresenter(fragment, accountList);
        return fragment;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_list, container, false);
        if (presenter == null) dismiss();
        else presenter.start();
        return rootView;
    }

    @Override
    public void onDestroy() {

        Singleton.log("AccountListFragment onDestroy");

        super.onDestroy();
    }

    @Override
    public void findView() {
        accountListView = (ListView) rootView.findViewById(R.id.accountListView);
        if (title.length() > 0) setTitle(title);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ((AccountListAdapter) accountListView.getAdapter()).notifyDataSetChanged();
        }

    }

    @Override
    public void setAccountListView() {
        adapter = new AccountListAdapter();
        presenter.adapterLoadData(adapter);
        accountListView.setAdapter(adapter);
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0)
                    showDetailFragment(adapter.getItem(position));
            }
        });
        accountListView.setOnItemLongClickListener(longClickListener);
    }

    public void showDetailFragment(Account account) {
        AccountDetailFragment fragment = AccountDetailFragment.newInstance(account, false);
        fragment.show(getFragmentManager(), AccountDetailFragment.class.getName());
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fragmentContainerRelativeLayout, fragment, AccountDetailFragment.class.getName());
//        transaction.hide(this);
//        transaction.addToBackStack(this.getClass().getName());
//        transaction.commit();
    }

    AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            if (position > 0) showDialogForDelete(position);
            return true;
        }
    };

    public void showDialogForDelete(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.delete);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteAccount(position);
                adapter.notifyDataSetChanged();
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });
        builder.create().show();
    }


}
