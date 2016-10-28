package com.aidan.aidanenvelopesavemoney.Information;

import com.aidan.aidanenvelopesavemoney.DevelopTool.Singleton;
import com.aidan.aidanenvelopesavemoney.Model.Account;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.List;

/**
 * Created by s352431 on 2016/10/18.
 */
public class InformationPresenter implements InformationContract.presenter {
    public static final String fileName = "LVBEnvelopeSaveMoney";
    InformationContract.view view;
    InformationModel model;

    public InformationPresenter(InformationContract.view view) {
        this.view = view;
        model = new InformationModel();
    }


    @Override
    public void start() {
        view.findView();
        view.setViewClick();
        setMonthCost();
    }

    @Override
    public void createExcelButtonClick(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                WriteExcel writeExcel = new WriteExcel(model);
                writeExcel.createExcel(path);
                view.showToast(R.string.complete);
            }
        }).start();

    }

    @Override
    public void readExcelButtonClick(final String path) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ReadExcel readExcel = new ReadExcel();
                readExcel.read(path);
                view.showToast(R.string.complete);
            }
        }).start();
    }

    public void setMonthCost() {
        List<Envelope> envelopes = model.getEnvelopeList();
        int budget = 0;
        int monthCost = 0;
        int sup;
        int todayCost =0;
        for (Envelope envelope : envelopes) {
            budget += envelope.getMax();
            monthCost += envelope.getCost();
        }
        for (Account account : model.getAccountList()){
            if(account.isToday())
                todayCost += account.getCost();
        }
        sup = budget - monthCost;
        view.setMonthInformation(budget, monthCost, sup,todayCost);

    }

}
