package com.aidan.aidanenvelopesavemoney.EnvelopeList;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aidan.aidanenvelopesavemoney.Model.Account;
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
    public View getView(int position, View convertView,final ViewGroup parent) {
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
        final Envelope envelope = envelopeList.get(position);
        viewHolder.setViewHolder(envelopeList.get(position));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNewAccountDialog(envelope,parent);
            }
        });
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                showSetEnvelopDialog(envelope);
                return false;
            }
        });
        return convertView;
    }
    public void showSetEnvelopDialog(Envelope envelope){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_set_envelop, null);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        TextView titleTextView = (TextView) dialogView.findViewById(R.id.titleTextView);
        TextView setValueTextView = (TextView) dialogView.findViewById(R.id.setValueTextView);
        TextView deleteTextView = (TextView) dialogView.findViewById(R.id.deleteTextView);
        TextView cancelTextView = (TextView) dialogView.findViewById(R.id.cancelTextView);
        titleTextView.setText("選取了[ "+envelope.getName() + " ] 信封");
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void showNewAccountDialog(Envelope envelope,ViewGroup parent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View dialogView = inflater.inflate(R.layout.dialog_new_account, parent,false);
        builder.setView(dialogView);
        final AlertDialog dialog = builder.create();
        setNewAccountDialog(dialogView,envelope,dialog);
        dialog.show();
    }
    public void setNewAccountDialog(View view, final Envelope envelope, final AlertDialog dialog){
        TextView nameTextView = (TextView)view.findViewById(R.id.nameTextView);
        TextView maxTextView = (TextView)view.findViewById(R.id.maxTextView);
        final EditText costEditText = (EditText)view.findViewById(R.id.costEditText);
        final EditText commentEditText = (EditText)view.findViewById(R.id.commentEditText);
        TextView okTextView = (TextView)view.findViewById(R.id.okTextView);
        TextView cancelTextView = (TextView)view.findViewById(R.id.cancelTextView);
        nameTextView.setText(envelope.getName());
        maxTextView.setText((envelope.getMax() - envelope.getCost())+"");
        okTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account();
                String cost = costEditText.getText().toString();
                String comment = commentEditText.getText().toString();
                account.setCost(Integer.valueOf(cost));
                account.setComment(comment);
                account.setEnvelopeName(envelope.getName());
                envelope.addAccount(account);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
            float under = envelope.getMax() == 0 ? 1 : envelope.getMax();
            percentTextView.setText(String.valueOf(sup *100.0 / under) + "%");
        }


    }
}
