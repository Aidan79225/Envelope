package com.aidan.aidanenvelopesavemoney.MainPageManager;

/**
 * Created by s352431 on 2016/10/4.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.R;


public class TabView extends RelativeLayout {

    public interface TabViewListener {
        void didClickOnTabView(int position);
    }

    private int position = 0;
    RelativeLayout tabContainer;
    ImageView tabImageView;
    TextView tabTextView;
    TextView badgeTextView;
    private TabViewListener tabViewListener;

    public TabView(Context context) {
        super(context);
        initViews();
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        View.inflate(getContext(), R.layout.tab, this);

        tabContainer = (RelativeLayout) findViewById(R.id.rootContainer);
        tabImageView = (ImageView) findViewById(R.id.tabImageView);
        tabTextView = (TextView) findViewById(R.id.tabTextView);
        badgeTextView = (TextView) findViewById(R.id.badgeTextView);
        badgeTextView.setVisibility(View.GONE);
        //set listener
        tabContainer.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (tabViewListener != null) {
                    tabViewListener.didClickOnTabView(position);
                }
            }
        });
    }

    public void setBadge(int badge) {
        if (badge <= 0) {
            badgeTextView.setVisibility(View.GONE);
        } else if (badge >= 100) {
            badgeTextView.setText("99+");
            badgeTextView.setVisibility(View.VISIBLE);
        } else {
            badgeTextView.setText(badge + "");
            badgeTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setValues(String tabName, int tabImageId, int position) {
        tabImageView.setImageResource(tabImageId);
//		tabImageView.setImageDrawable(getResources().getDrawable(tabImageId));
        tabTextView.setText(tabName);
        this.position = position;
    }

    public void setTabViewListener(TabViewListener tabViewListener) {
        this.tabViewListener = tabViewListener;
    }


}
