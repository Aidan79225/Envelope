package com.aidan.aidanenvelopesavemoney;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.aidan.aidanenvelopesavemoney.EnvelopeList.EnvelopeListFragment;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout fragmentContainerRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void onResume(){
        super.onResume();
        findView();
        loadEnvelopeListFragment();
    }
    public void findView(){
        fragmentContainerRelativeLayout = (RelativeLayout)findViewById(R.id.fragmentContainerRelativeLayout);
    }
    public void loadEnvelopeListFragment(){
        Fragment fragment = new EnvelopeListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragmentContainerRelativeLayout, fragment, EnvelopeListFragment.class.getName());
        transaction.commit();
    }

}
