package com.aidan.aidanenvelopesavemoney.EnvelopeList;

/**
 * Created by Aidan on 2016/10/1.
 */

public interface EnvelopeListContract {
    interface view {
        void findView();

        void setEnvelopeGridView();

        void setViewClick();

        void setMonthInformation(int budget, int cost, int sup);
    }

    interface presenter {
        void setAdapter(EnvelopeAdapter adapter);

        void start();

        void addEnvelopButtonClick(String name, String max);

        void adapterLoadData();
    }

    interface newData {
        void update();
    }
}
