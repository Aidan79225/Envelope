package com.aidan.aidanenvelopesavemoney.Information;

/**
 * Created by s352431 on 2016/10/18.
 */
public interface InformationContract {
    interface view {
        void findView();

        void setViewClick();

        void setMonthInformation(int budget, int cost, int sup,int today);

        void showToast(int msg);
    }

    interface presenter {
        void start();

        void createExcelButtonClick(String path);

        void readExcelButtonClick(String path);

    }
}
