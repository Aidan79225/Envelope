package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;

/**
 * Created by Aidan on 2016/10/1.
 */

public class EnvelopeListPresenter implements EnvelopeListContract.presenter {
    private EnvelopeListContract.view view;
    private EnvelopeListModel model;
    private EnvelopeAdapter adapter;
    public EnvelopeAdapter getAdapter() {
        return adapter;
    }



    public EnvelopeListPresenter(EnvelopeListContract.view view){
        this.view = view;
        model = new EnvelopeListModel();
    }

    @Override
    public void setAdapter(EnvelopeAdapter adapter) {
        this.adapter = adapter;
        model.addUpdateList(adapter);
    }

    @Override
    public void start() {
        model.loadFromDB();
        view.findView();
        view.setEnvelopeGridView();
        view.setViewClick();
    }

    @Override
    public void addEnvelopButtonClick(String name, String max) {
        if(name.length() == 0 )name = "未設置名稱";
        if(max.length() == 0 )max = "0.00";

        Envelope envelope = new Envelope();
        envelope.setName(name);
        float fmax = Float.parseFloat(max);
        envelope.setMax((int)fmax);
        model.addEnvelope(envelope);

    }

    @Override
    public void adapterLoadData(){
        adapter.setEnvelopeList(model.getEnvelopeList());
    }

    @Override
    public void saveData() {
        model.saveToDB();
    }


}
