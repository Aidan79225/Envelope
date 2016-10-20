package com.aidan.aidanenvelopesavemoney.MainPageManager;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.aidan.aidanenvelopesavemoney.R;

import java.util.HashSet;

public class TabBar extends RelativeLayout implements TabView.TabViewListener {

    public interface TabBarListener {
        void didClickOnTab(int clickedTabIndex);
    }

    private TabBarListener tabBarListener;
    private int currentTab = 0;
    private Context context;
    private LinearLayout tabBarContainer;

    private HashSet<Integer> noUseTabs = new HashSet<Integer>();

    public TabBar(Context context) {
        super(context);
        this.context = context;
    }

    public TabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        View rootView = LayoutInflater.from(context).inflate(R.layout.tab_bar, this, true);
        tabBarContainer = (LinearLayout) rootView.findViewById(R.id.tabBarContainer);


    }

    public TabBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addTabs(String[] tabNames, int[] tabImageIds) {
        for (int i = 0; i < tabNames.length; i++) {
            addTab(tabBarContainer, tabNames[i], tabImageIds[i]);
        }

        reloadUI();
    }

    public void addTab(LinearLayout container, String tabName, int tabImageId) {

        TabView tabView = new TabView(context);
        tabView.setValues(tabName, tabImageId, container.getChildCount());
        tabView.setTabViewListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        container.addView(tabView, params);
    }


    @Override
    public void didClickOnTabView(int position) {

//        if (currentTab == position) {
//            return;
//        }

        //detect no use tab
        if (noUseTabs.contains(Integer.valueOf(position))) {
            if (tabBarListener != null) {
                tabBarListener.didClickOnTab(position);
            }
            return;
        }

        currentTab = position;
        reloadUI();
        if (tabBarListener != null) {
            tabBarListener.didClickOnTab(position);
        }
        saveTabPosition();
    }

    public void setTabBarListener(TabBarListener newTabBarListener) {
        tabBarListener = newTabBarListener;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    private void saveTabPosition() {

    }

    public void setCurrentTab(int newTabIndex) {
        currentTab = newTabIndex;
        reloadUI();
        saveTabPosition();
    }

    private void reloadUI() {
        for (int i = 0; i < tabBarContainer.getChildCount(); i++) {
            View tab = tabBarContainer.getChildAt(i);
            if (currentTab == i) {
                tab.setSelected(true);
            } else {
                tab.setSelected(false);
            }
        }
    }


    public void setNoUseTab(int tabIndex) {
        this.noUseTabs.add(Integer.valueOf(tabIndex));
    }

}

