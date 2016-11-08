package com.aidan.aidanenvelopesavemoney.HistoryList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;
import com.aidan.aidanenvelopesavemoney.Model.MonthHistory;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by s352431 on 2016/11/8.
 */
public class HistoryMonthEnvelopeAdapter extends BaseAdapter {
    List<Envelope> envelopeList = new ArrayList<>();
    Context context;
    private LayoutInflater inflater;
    public HistoryMonthEnvelopeAdapter(Context context,MonthHistory monthHistory){
        this.context = context;
        inflater = LayoutInflater.from(context);
        envelopeList.addAll(monthHistory.getEnvelopeList());

    }
    @Override
    public int getCount() {
        return envelopeList.size()+1;
    }

    @Override
    public Envelope getItem(int position) {
        return envelopeList.get(position-1);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(context);
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_history_envelop, parent, false);
            viewHolder.nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            viewHolder.maxTextView = (TextView) view.findViewById(R.id.maxTextView);
            viewHolder.surplusTextView = (TextView)view.findViewById(R.id.surplusTextView);
            viewHolder.costTextView = (TextView)view.findViewById(R.id.costTextView);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (position == 0) {
            viewHolder.nameTextView.setText(R.string.envelop_name);
            viewHolder.maxTextView.setText(R.string.envelop_max);
        } else {
            Envelope envelope = getItem(position);
            viewHolder.nameTextView.setText(envelope.getName());
            viewHolder.maxTextView.setText(String.valueOf(envelope.getMax()));
            viewHolder.surplusTextView.setText(String.valueOf(envelope.getMax()-envelope.getCost()));
            viewHolder.costTextView.setText(String.valueOf(envelope.getCost()));
        }
        return view;
    }
    class ViewHolder {
        public TextView nameTextView;
        public TextView maxTextView;
        public TextView surplusTextView;
        public TextView costTextView;
    }
}
