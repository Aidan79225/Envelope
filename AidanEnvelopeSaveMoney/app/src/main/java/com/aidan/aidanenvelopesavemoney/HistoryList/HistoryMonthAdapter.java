package com.aidan.aidanenvelopesavemoney.HistoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.DataBase.LoadDataSingleton;
import com.aidan.aidanenvelopesavemoney.Model.MonthHistory;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.List;

/**
 * Created by s352431 on 2016/11/8.
 */
public class HistoryMonthAdapter extends BaseAdapter {
    private List<MonthHistory> monthHistoryList;
    private Context context;
    private LayoutInflater inflater;
    public HistoryMonthAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        monthHistoryList = LoadDataSingleton.getInstance().getMonthHistoryList();
    }
    @Override
    public int getCount() {
        return monthHistoryList.size()+1;
    }

    @Override
    public MonthHistory getItem(int position) {
        return monthHistoryList.get(position-1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_month, parent, false);
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            viewHolder.saveMoneyTextView = (TextView)view.findViewById(R.id.saveMoneyTextView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == 0) {
            viewHolder.nameTextView.setText(R.string.history);
            viewHolder.saveMoneyTextView.setText(R.string.month_save_money);
        } else {
            MonthHistory monthHistory = getItem(position);
            viewHolder.nameTextView.setText(monthHistory.getName());
            viewHolder.saveMoneyTextView.setText(String.valueOf(monthHistory.getSaveMoney()));
        }
        return view;
    }
    class ViewHolder {
        public TextView nameTextView;
        public TextView saveMoneyTextView;
    }
}
