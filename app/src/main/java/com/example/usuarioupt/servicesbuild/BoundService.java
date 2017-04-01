package com.example.usuarioupt.servicesbuild;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Chronometer;

/**
 * Created by USUARIOUPT on 01/04/2017.
 */

public class BoundService extends Service {

    private static String LOG_TAG = "BoundService";
    private IBinder mBinder = new MyBinder();
    private Chronometer mChronometer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(LOG_TAG, "inonBind");
        return mBinder;
    }

    public class MyBinder extends Binder{

        BoundService getService(){
            return BoundService.this;
        }
    }

    @Override
    public void onCreate(){
        super.onCreate();

        Log.v(LOG_TAG, "in onCreate");
        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public void onRebind(Intent intent){
        Log.v(LOG_TAG, "in onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent){
        Log.v(LOG_TAG, "in onUnbind");
        return  super.onUnbind(intent);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.v(LOG_TAG, "in onDestroy");
        mChronometer.stop();
    }

    public String getTimeStamp(){
        long elapsedMillis = SystemClock.elapsedRealtime()
                - mChronometer.getBase();

        int horas = (int)(elapsedMillis/3600000);
        int minutos = (int)(elapsedMillis-horas*3600000)/60000;
        int segundos = (int)(elapsedMillis-horas*3600000-minutos*60000)/1000;
        int milis = (int)(elapsedMillis-horas*3600000-minutos*60000-segundos*1000);

        return horas+" : "+minutos+" : "+segundos+" : "+milis;
    }
}
