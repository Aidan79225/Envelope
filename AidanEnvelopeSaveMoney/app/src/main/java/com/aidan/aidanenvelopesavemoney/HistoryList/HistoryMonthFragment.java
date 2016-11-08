package com.aidan.aidanenvelopesavemoney.HistoryList;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.aidan.aidanenvelopesavemoney.AccountDetail.AccountDetailFragment;
import com.aidan.aidanenvelopesavemoney.AccountList.AccountListAdapter;
import com.aidan.aidanenvelopesavemoney.Interface.OnBackPressedListener;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.MonthHistory;
import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by s352431 on 2016/11/8.
 */
public class HistoryMonthFragment extends DialogFragment implements HistoryMonthContract.view,OnBackPressedListener {
    ViewGroup rootView;
    HistoryMonthContract.presenter presenter;
    ListView monthListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_month_list, container, false);
        presenter = new HistoryMonthPresenter(this);
        presenter.start();
        return rootView;
    }

    @Override
    public void findView() {
        monthListView = (ListView) rootView.findViewById(R.id.monthListView);
    }

    @Override
    public void setListView() {
        monthListView.setAdapter((ListAdapter) presenter.getAdapter(getActivity()));
        monthListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) monthToEnvelope(position);
            }
        });
    }

    public void monthToEnvelope(int position) {
        final HistoryMonthEnvelopeAdapter adapter = new HistoryMonthEnvelopeAdapter(getActivity(), (MonthHistory) monthListView.getAdapter().getItem(position));
        monthListView.setAdapter(adapter);
        monthListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) envelopeToAccount(adapter, position);
            }
        });
    }

    public void envelopeToAccount(HistoryMonthEnvelopeAdapter adapter, int position) {
        final AccountListAdapter accountListAdapter = new AccountListAdapter(getActivity());
        accountListAdapter.setAccountList(presenter.findEnvelopsAccount(adapter.getItem(position)));
        monthListView.setAdapter(accountListAdapter);
        monthListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) showDetailFragment(accountListAdapter.getItem(position));
            }
        });
    }

    public void showDetailFragment(Account account) {
        AccountDetailFragment fragment = AccountDetailFragment.newInstance(account);
        fragment.show(getFragmentManager(),AccountDetailFragment.class.getName());
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.add(R.id.fragmentContainerRelativeLayout, fragment, AccountDetailFragment.class.getName());
//        transaction.hide(this);
//        transaction.addToBackStack(this.getClass().getName());
//        transaction.commit();
    }





    @Override
    public boolean onBackPressed() {
        if (!(monthListView.getAdapter() instanceof HistoryMonthAdapter)) {
            setListView();
            return true;
        } else {
            return false;
        }
    }
}
