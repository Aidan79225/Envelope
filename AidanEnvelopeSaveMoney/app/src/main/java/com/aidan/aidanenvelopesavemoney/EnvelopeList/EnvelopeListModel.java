package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 2016/10/2.
 */

public class EnvelopeListModel {
    private List<Envelope> envelopeList = new ArrayList<>();
    private List<EnvelopeListContract.newData> updateList = new ArrayList<>();
    public void addUpdateList(EnvelopeListContract.newData newData){
        updateList.add(newData);
    }
    public void addEnvelope(Envelope envelope){
        envelopeList.add(envelope);
        for(EnvelopeListContract.newData newData : updateList)
                newData.update();
    }
    public List<Envelope> getEnvelopeList(){
        return envelopeList;
    }
}
