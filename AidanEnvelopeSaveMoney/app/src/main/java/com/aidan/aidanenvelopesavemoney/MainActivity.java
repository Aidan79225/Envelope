package com.aidan.aidanenvelopesavemoney;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.EnvelopeDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.EnvelopeList.EnvelopeListFragment;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout fragmentContainerRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnvelopeDAO.init(getApplicationContext());
        AccountDAO.init(getApplicationContext());
        LoadDataSingleton.getInstance().loadFromDB();
    }
    @Override
    public void onDestroy(){
        LoadDataSingleton.getInstance().saveToDB();
        try{
            EnvelopeDAO.getInstance().close();
            AccountDAO.getInstance().close();
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        super.onDestroy();
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
