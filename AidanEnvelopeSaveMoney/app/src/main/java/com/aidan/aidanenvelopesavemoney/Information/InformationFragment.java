package com.aidan.aidanenvelopesavemoney.Information;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Network.OneDriveService;
import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by s352431 on 2016/10/18.
 */
public class InformationFragment extends DialogFragment implements InformationContract.view {
    InformationContract.presenter presenter;
    ViewGroup rootView;
    Button createExcelButton, readExcelButton,uploadToOneDriveButton,downFromOneDriveButton;
    private TextView monthCostTextView, monthBudgetTextView, monthSurplusTextView,todayCostTextView;

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle s) {
        super.onCreate(s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_information, container, false);
        presenter = new InformationPresenter(this);
        presenter.start();
        return rootView;
    }


    @Override
    public void findView() {
        downFromOneDriveButton = (Button)rootView.findViewById(R.id.downFromOneDriveButton);
        createExcelButton = (Button) rootView.findViewById(R.id.createExcelButton);
        readExcelButton = (Button) rootView.findViewById(R.id.readExcelButton);
        uploadToOneDriveButton = (Button)rootView.findViewById(R.id.uploadToOneDriveButton);
        monthCostTextView = (TextView) rootView.findViewById(R.id.monthCostTextView);
        monthBudgetTextView = (TextView) rootView.findViewById(R.id.monthBudgetTextView);
        monthSurplusTextView = (TextView) rootView.findViewById(R.id.monthSurplusTextView);
        todayCostTextView = (TextView) rootView.findViewById(R.id.todayCostTextView);
    }

    @Override
    public void setViewClick() {
        createExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.createExcelButtonClick(Environment.getExternalStorageDirectory().getAbsolutePath());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        readExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    presenter.readExcelButtonClick(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + WriteExcel.fileName + ".xls");
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
        uploadToOneDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OneDriveService.getInstance().upload(getActivity());
                    }
                }).start();
            }
        });
        downFromOneDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OneDriveService.getInstance().download(getActivity());

                    }
                }).start();
            }
        });
    }

    @Override
    public void setMonthInformation(int budget, int cost, int sup,int today) {
        monthBudgetTextView.setText(String.valueOf(budget));
        monthCostTextView.setText(String.valueOf(cost));
        monthSurplusTextView.setText(String.valueOf(sup));
        todayCostTextView.setText(String.valueOf(today));
    }
}
