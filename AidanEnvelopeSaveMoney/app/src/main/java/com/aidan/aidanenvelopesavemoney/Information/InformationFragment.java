package com.aidan.aidanenvelopesavemoney.Information;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aidan.aidanenvelopesavemoney.Network.OneDriveService;
import com.aidan.aidanenvelopesavemoney.R;

/**
 * Created by s352431 on 2016/10/18.
 */
public class InformationFragment extends DialogFragment implements InformationContract.view {
    InformationContract.presenter presenter;
    ViewGroup rootView;
    Button createExcelButton, readExcelButton,uploadToOneDriveButton,downFromOneDriveButton;
    private TextView monthCostTextView, monthBudgetTextView, monthSurplusTextView,todayCostTextView;

    public static InformationFragment newInstance() {
        InformationFragment fragment = new InformationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle s) {
        super.onCreate(s);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_information, container, false);
        presenter = new InformationPresenter(this);
        presenter.start();
        return rootView;
    }


    @Override
    public void findView() {
        downFromOneDriveButton = (Button)rootView.findViewById(R.id.downFromOneDriveButton);
        createExcelButton = (Button) rootView.findViewById(R.id.createExcelButton);
        readExcelButton = (Button) rootView.findViewById(R.id.readExcelButton);
        uploadToOneDriveButton = (Button)rootView.findViewById(R.id.uploadToOneDriveButton);
        monthCostTextView = (TextView) rootView.findViewById(R.id.monthCostTextView);
        monthBudgetTextView = (TextView) rootView.findViewById(R.id.monthBudgetTextView);
        monthSurplusTextView = (TextView) rootView.findViewById(R.id.monthSurplusTextView);
        todayCostTextView = (TextView) rootView.findViewById(R.id.todayCostTextView);
    }
    Callback createExcel = new Callback() {
        @Override
        public void todo() {
            try {
                presenter.createExcelButtonClick(Environment.getExternalStorageDirectory().getAbsolutePath());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    };
    Callback readExcel = new Callback() {
        @Override
        public void todo() {
            try {
                presenter.readExcelButtonClick(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + WriteExcel.fileName + ".xls");
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    };
    Callback upload = new Callback() {
        @Override
        public void todo() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OneDriveService.getInstance().upload(getActivity());
                }
            }).start();
        }
    };
    Callback download = new Callback() {
        @Override
        public void todo() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OneDriveService.getInstance().download(getActivity());
                }
            }).start();
        }
    };

    @Override
    public void setViewClick() {
        createExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.create_excel,R.string.create_excel,createExcel);
            }
        });
        readExcelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.read_excel,R.string.read_excel,readExcel);
            }
        });
        uploadToOneDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.upload_to_one_drive,R.string.upload_to_one_drive,upload);
            }
        });
        downFromOneDriveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(R.string.download_from_one_drive,R.string.download_from_one_drive,download);
            }
        });
    }
    private void showDialog(int title,int msg, final Callback callback){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.create();
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                callback.todo();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    @Override
    public void setMonthInformation(int budget, int cost, int sup,int today) {
        monthBudgetTextView.setText(String.valueOf(budget));
        monthCostTextView.setText(String.valueOf(cost));
        monthSurplusTextView.setText(String.valueOf(sup));
        todayCostTextView.setText(String.valueOf(today));
    }

    @Override
    public void showToast(final int msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    interface Callback{
        void todo();
    }
}
