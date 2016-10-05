package com.aidan.aidanenvelopesavemoney.MainPageManager;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TabHost;

import com.aidan.aidanenvelopesavemoney.AccountList.AccountListFragment;
import com.aidan.aidanenvelopesavemoney.DataBase.AccountDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.EnvelopeDAO;
import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.EnvelopeList.EnvelopeListFragment;
import com.aidan.aidanenvelopesavemoney.Model.Envelope;
import com.aidan.aidanenvelopesavemoney.R;

public class MainActivity extends AppCompatActivity implements TabBar.TabBarListener {
    private RelativeLayout fragmentContainerRelativeLayout;
    private TabBar tabBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EnvelopeDAO.init(getApplicationContext());
        AccountDAO.init(getApplicationContext());
        LoadDataSingleton.getInstance().loadFromDB();
        setTabBar();
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
    public void setTabBar(){
        tabBar = (TabBar)findViewById(R.id.tabBar);
        tabBar.addTabs(new String[]{getResources().getString(R.string.envelop),getResources().getString(R.string.account)},new int[]{R.mipmap.envelop2 ,R.mipmap.envelop});
        tabBar.setTabBarListener(this);
    }
    public void findView(){
        fragmentContainerRelativeLayout = (RelativeLayout)findViewById(R.id.fragmentContainerRelativeLayout);

    }
    public void loadEnvelopeListFragment(){
        Fragment fragment = new EnvelopeListFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int backStackCount = getFragmentManager().getBackStackEntryCount();
        for(int i = 0; i < backStackCount; i++) {
            fragmentManager.popBackStack();
        }
        transaction.replace(R.id.fragmentContainerRelativeLayout, fragment, EnvelopeListFragment.class.getName());
        transaction.commit();
    }
    public void loadAccountListFragment(){
        Fragment fragment = AccountListFragment.newInstance(LoadDataSingleton.getInstance().getAccountList());
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        int backStackCount = getFragmentManager().getBackStackEntryCount();
        for(int i = 0; i < backStackCount; i++) {
            fragmentManager.popBackStack();
        }
        transaction.replace(R.id.fragmentContainerRelativeLayout, fragment, AccountListFragment.class.getName());
        transaction.commit();
    }


    @Override
    public void didClickOnTab(int clickedTabIndex) {
        switch (clickedTabIndex){
            case 0:
                loadEnvelopeListFragment();
                break;
            case 1:
                loadAccountListFragment();
                break;
            default:
                break;
        }
    }

}
