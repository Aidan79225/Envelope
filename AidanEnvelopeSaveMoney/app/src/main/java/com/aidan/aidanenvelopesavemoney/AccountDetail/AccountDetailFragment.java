package com.aidan.aidanenvelopesavemoney.AccountDetail;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by Aidan on 2016/10/4.
 */

public class AccountDetailFragment extends DialogFragment implements AccountDetailContract.view {
    ViewGroup rootView;
    AccountDetailContract.presenter presenter;
    TextView envelopNameTextView, dateTextView;
    EditText commentEditText, costEditText;
    ImageView photoImageView;
    LinearLayout photoLinearLayout;
    Button changeButton;
    boolean cannotModify = false;

    public static AccountDetailFragment newInstance(Account account, boolean flag) {
        AccountDetailFragment fragment = new AccountDetailFragment();
        fragment.presenter = new AccountDetailPresenter(fragment);
        fragment.presenter.setData(account);
        fragment.cannotModify = flag;
        return fragment;
    }

    @Override
    public void onCreate(Bundle s) {
        super.onCreate(s);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_account_detail, container, false);
        if (presenter == null) dismiss();
        else presenter.start();
        return rootView;
    }

    @Override
    public void findView() {
        envelopNameTextView = (TextView) rootView.findViewById(R.id.envelopNameTextView);
        dateTextView = (TextView) rootView.findViewById(R.id.dateTextView);
        commentEditText = (EditText) rootView.findViewById(R.id.commentEditText);
        costEditText = (EditText) rootView.findViewById(R.id.costEditText);
        photoImageView = (ImageView) rootView.findViewById(R.id.photoImageView);
        photoLinearLayout = (LinearLayout) rootView.findViewById(R.id.photoLinearLayout);
        changeButton = (Button) rootView.findViewById(R.id.changeButton);
        if (cannotModify) changeButton.setVisibility(View.GONE);
    }

    @Override
    public void setView(Account account) {
        envelopNameTextView.setText(account.getEnvelopeName());
        dateTextView.setText(account.getDate());
        commentEditText.setText(account.getComment());
        costEditText.setText(account.getCost() + "");
        commentEditText.setInputType(InputType.TYPE_NULL);
        costEditText.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public void setViewClick() {
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.changeButtonClick();
            }
        });
    }

    @Override
    public void prepareChange() {
        commentEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        changeButton.setText(R.string.save);
        costEditText.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
    }

    @Override
    public void saveChange() {
        String cost = costEditText.getText().toString();
        String comment = commentEditText.getText().toString();
        if (cost.length() == 0) {
            Toast.makeText(getActivity(), R.string.cost_cant_be_void, Toast.LENGTH_SHORT).show();
            return;
        }
        hideKey(commentEditText);
        commentEditText.setInputType(InputType.TYPE_NULL);
        costEditText.setInputType(InputType.TYPE_NULL);
        presenter.setAccount(cost, comment);
        changeButton.setText(R.string.edit);
    }

    public void hideKey(EditText editText) {
        try {
            InputMethodManager input = (InputMethodManager) getActivity()
                    .getSystemService(Activity.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
