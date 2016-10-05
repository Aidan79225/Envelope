package com.aidan.aidanenvelopesavemoney.AccountList;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
    ListView accountListView;
    String title = "";
    public static AccountListFragment newInstance(List<Account> accountList){
        AccountListFragment fragment = new AccountListFragment();
        fragment.presenter = new AccountListPresenter(fragment,accountList);
        return fragment;
    }
    public void setTitle(String title){
        this.title = title;
    }
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_list, container, false);
        if(presenter == null)dismiss();
        else presenter.start();
        return rootView;
    }


    @Override
    public void findView() {
        accountListView = (ListView)rootView.findViewById(R.id.accountListView);
        if(title.length() > 0)setTitle(title);
    }
    @Override
    public void onResume(){
        super.onResume();
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ((AccountListAdapter)accountListView.getAdapter()).notifyDataSetChanged();
        }

    }

    @Override
    public void setAccountListView() {
        final AccountListAdapter adapter = new AccountListAdapter(getActivity());
        presenter.setAdapter(adapter);
        presenter.adapterLoadData();
        accountListView.setAdapter(adapter);
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0)
                showDetailFragment(adapter.getItem(position));
            }
        });
    }
    public void showDetailFragment(Account account){
        AccountDetailFragment fragment = AccountDetailFragment.newInstance(account);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerRelativeLayout, fragment, AccountDetailFragment.class.getName());
        transaction.hide(this);
        transaction.addToBackStack(this.getClass().getName());
        transaction.commit();
    }




}
