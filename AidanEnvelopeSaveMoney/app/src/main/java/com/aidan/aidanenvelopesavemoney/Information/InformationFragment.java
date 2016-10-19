package com.aidan.aidanenvelopesavemoney.Information;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by s352431 on 2016/10/18.
 */
public class InformationFragment extends DialogFragment implements InformationContract.view<InformationContract.presenter>{
    InformationContract.presenter presenter;
    ViewGroup rootView;

    @Override
    public void onCreate(Bundle s){
        super.onCreate(s);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_information,container,true);
        return rootView;
    }


}
