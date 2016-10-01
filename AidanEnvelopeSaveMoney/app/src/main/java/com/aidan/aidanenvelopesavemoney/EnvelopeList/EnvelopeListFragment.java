package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by Aidan on 2016/10/1.
 */

public class EnvelopeListFragment extends Fragment implements EnvelopeListContract.view {
    private View rootView;
    private GridView envelopesGridView;
    private Button addEnvelopeButton;
    public EnvelopeListContract.presenter presenter;
    @Override
    public void onCreate(Bundle s){
        super.onCreate(s);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_envelope_list, container, false);
        if(presenter == null){
            presenter = new EnvelopeListPresenter(this);
        }
        presenter.start();
        return rootView;
    }

    @Override
    public void findView() {
        envelopesGridView = (GridView) rootView.findViewById(R.id.envelopesGridView);
        addEnvelopeButton =(Button)rootView.findViewById(R.id.addEnvelopeButton);
    }

    @Override
    public void setEnvelopeGridView() {
        EnvelopeAdapter adapter = new EnvelopeAdapter(getActivity());
        presenter.setAdapter(adapter);
        presenter.adapterLoadData();
        envelopesGridView.setAdapter(adapter);
    }

    @Override
    public void setViewClick() {
        addEnvelopeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.addEnvelopButtonClick();
            }
        });
    }

}
