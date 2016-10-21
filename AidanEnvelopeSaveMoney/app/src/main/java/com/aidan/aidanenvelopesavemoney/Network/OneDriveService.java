package com.aidan.aidanenvelopesavemoney.Network;

import android.app.Activity;
import android.os.Environment;
import android.os.Looper;
import android.widget.Toast;

import com.aidan.aidanenvelopesavemoney.Information.WriteExcel;
import com.onedrive.sdk.authentication.MSAAuthenticator;
import com.onedrive.sdk.concurrency.IProgressCallback;
import com.onedrive.sdk.core.ClientException;
import com.onedrive.sdk.core.DefaultClientConfig;
import com.onedrive.sdk.core.IClientConfig;
import com.onedrive.sdk.core.OneDriveErrorCodes;
import com.onedrive.sdk.extensions.IOneDriveClient;
import com.onedrive.sdk.extensions.Item;
import com.onedrive.sdk.extensions.OneDriveClient;
import com.onedrive.sdk.options.Option;
import com.onedrive.sdk.options.QueryOption;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Collections;

/**
 * Created by s352431 on 2016/10/21.
 */
public class OneDriveService {
    private static final String clientId = "1affa274-d5fb-47ec-baf9-6f1f4d5f2394";
    private static final String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + WriteExcel.fileName + ".xls";
    final MSAAuthenticator msaAuthenticator = new MSAAuthenticator() {
        @Override
        public String getClientId() {
            return clientId;
        }

        @Override
        public String[] getScopes() {
            return new String[]{"onedrive.readwrite", "onedrive.appfolder", "wl.offline_access"};
        }
    };

    final IClientConfig config = DefaultClientConfig.createWithAuthenticator(msaAuthenticator);
    private static OneDriveService oneDriveService;
    private OneDriveService(){

    }
    public static OneDriveService getInstance(){
        synchronized (OneDriveService.class){
            if(oneDriveService == null){
                oneDriveService = new OneDriveService();
            }
        }
        return oneDriveService;
    }

    public void upload(final Activity activity) {
        IOneDriveClient oneDriveClient = new OneDriveClient.Builder()
                .fromConfig(config)
                .loginAndBuildClient(activity);
        try {

            RandomAccessFile file = new RandomAccessFile(fileName, "r");
            byte[] fileInMemory = new byte[(int) file.length()];
            file.readFully(fileInMemory);
            final Option option = new QueryOption("@name.conflictBehavior", "replace");

            oneDriveClient
                    .getDrive()
                    .getItems("root")
                    .getChildren()
                    .byId(WriteExcel.fileName + ".xls")
                    .getContent()
                    .buildRequest(Collections.singletonList(option))
                    .put(fileInMemory,
                            new IProgressCallback<Item>() {
                                @Override
                                public void success(final Item item) {
                                    Toast.makeText(activity, "上傳完成", Toast.LENGTH_LONG).show();

                                }

                                @Override
                                public void failure(final ClientException error) {
                                    if (error.isError(OneDriveErrorCodes.NameAlreadyExists)) {
                                        Toast.makeText(activity,
                                                "檔名衝突",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(activity, "上傳失敗", Toast.LENGTH_LONG).show();
                                        error.printStackTrace();
                                    }
                                }

                                @Override
                                public void progress(final long current, final long max) {
//                                dialog.setProgress((int) current);
//                                dialog.setMax((int) max);
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    public void download(Activity activity){
        InputStream is = null;
        OutputStream os = null;
        try{
            Looper.prepare();
            IOneDriveClient oneDriveClient = new OneDriveClient.Builder()
                    .fromConfig(config)
                    .loginAndBuildClient(activity);
            is = oneDriveClient.getDrive().getItems("root").getChildren().byId(WriteExcel.fileName + ".xls").getContent().buildRequest().get();
            File file  = new File(fileName);
            os = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            Toast.makeText(activity, "下載成功", Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(activity, "下載失敗", Toast.LENGTH_LONG).show();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            Looper.loop();
        }



    }
}
