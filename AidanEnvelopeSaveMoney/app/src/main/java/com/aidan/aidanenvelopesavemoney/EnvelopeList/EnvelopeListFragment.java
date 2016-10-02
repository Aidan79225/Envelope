package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;
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
                showNewEnvelopDialog();
            }
        });
        envelopesGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });

    }

    public void showNewEnvelopDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_envelop, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        final EditText nameEditText = (EditText) dialogView.findViewById(R.id.nameEditText);
        final EditText maxEditText = (EditText) dialogView.findViewById(R.id.maxEditText);
        TextView okTextView = (TextView) dialogView.findViewById(R.id.okTextView);
        TextView cancelTextView = (TextView) dialogView.findViewById(R.id.cancelTextView);
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String max = maxEditText.getText().toString();
                presenter.addEnvelopButtonClick(name,max);
                dialog.dismiss();
            }
        });
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        dialog.show();
    }




}
