package com.aidan.aidanenvelopesavemoney.AccountDetail;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.AccountList.AccountListFragment;
import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountDetailFragment extends DialogFragment implements AccountDetailContract.view {
    ViewGroup rootView;
    AccountDetailContract.presenter presenter;
    TextView envelopNameTextView,dateTextView;
    EditText commentEditText,costEditText;
    ImageView photoImageView;
    LinearLayout photoLinearLayout;
    Button changeButton;
    public static AccountDetailFragment newInstance(){
        AccountDetailFragment fragment = new AccountDetailFragment();
        fragment.presenter = new AccountDetailPresenter(fragment);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_detail, container, false);
        if(presenter == null)dismiss();
        else presenter.start();

        return rootView;
    }

    @Override
    public void findView() {
        envelopNameTextView = (TextView)rootView.findViewById(R.id.envelopNameTextView);
        dateTextView = (TextView)rootView.findViewById(R.id.dateTextView);
        commentEditText =  (EditText) rootView.findViewById(R.id.commentEditText);
        costEditText = (EditText)rootView.findViewById(R.id.costEditText);
        photoImageView= (ImageView) rootView.findViewById(R.id.photoImageView);
        photoLinearLayout= (LinearLayout) rootView.findViewById(R.id.photoLinearLayout);
        changeButton= (Button)rootView.findViewById(R.id.changeButton);
    }

}
