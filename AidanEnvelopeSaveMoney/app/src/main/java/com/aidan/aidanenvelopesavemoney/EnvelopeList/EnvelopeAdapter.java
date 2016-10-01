package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Model.Envelope;
import com.aidan.aidanenvelopesavemoney.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aidan on 2016/10/2.
 */

public class EnvelopeAdapter extends BaseAdapter implements EnvelopeListContract.newData{
    private List<Envelope> envelopeList = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    public  EnvelopeAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return envelopeList.size();
    }

    @Override
    public Envelope getItem(int position) {
        return envelopeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_envelope,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.nameTextView = (TextView) convertView.findViewById(R.id.nameTextView);
            viewHolder.surplusTextView = (TextView) convertView.findViewById(R.id.surplusTextView);
            viewHolder.percentTextView = (TextView) convertView.findViewById(R.id.percentTextView);
            viewHolder.iconImageView = (ImageView) convertView.findViewById(R.id.iconImageView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.setViewHolder(envelopeList.get(position));

        return convertView;
    }
    public void setEnvelopeList(List<Envelope> envelopeList){
        this.envelopeList = envelopeList;
    }

    @Override
    public void update() {
        notifyDataSetChanged();
    }

    public class ViewHolder{
        public TextView nameTextView;
        public TextView surplusTextView;
        public TextView percentTextView;
        public ImageView iconImageView;
        public void setViewHolder(Envelope envelope){
            float sup = envelope.getMax()-envelope.getCost();
            nameTextView.setText(envelope.getName());
            surplusTextView.setText(String.valueOf(sup));
            percentTextView.setText(String.valueOf(sup *100.0 / envelope.getMax()) + "%");
        }
    }
}
