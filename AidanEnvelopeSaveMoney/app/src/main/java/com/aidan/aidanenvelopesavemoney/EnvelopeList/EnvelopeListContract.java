package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;

/**
 * Created by Aidan on 2016/10/1.
 */

public interface EnvelopeListContract {
    interface view{
        void findView();
        void setEnvelopeGridView();
        void setViewClick();
    }
    interface presenter{
        void setAdapter(EnvelopeAdapter adapter);
        void start();
        void addEnvelopButtonClick(String name,String max);
        void adapterLoadData();

    }
    interface newData{
        void update();
    }
}
