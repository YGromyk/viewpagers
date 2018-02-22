package com.fepeprog.viewpagers;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

/**
 * Created by fepeprog on 2/20/18.
 */

public class MyService extends IntentService {
    public static final String ACTION = "MYSERVICE_ACTION";
    private Handler mHandler = new Handler(Looper.myLooper());

    public MyService(String name) {
        super(name);
    }

    public MyService() {
        this("my service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        while (true){
            try {
                Thread.sleep(500000);
                if(intent.getAction().equals(ACTION)){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Hiiiiiiiii ^^", Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}