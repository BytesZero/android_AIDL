package com.zhengsonglan.android_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class AppService extends Service {
    String TAG=this.getClass().getSimpleName();
    public AppService() {
    }
    String data="默认值";
    boolean runing;

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind AppService");

        return new IMyAidlInterface.Stub() {
            @Override
            public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

            }

            @Override
            public void setData(String data) throws RemoteException {
                AppService.this.data=data;
            }
        };
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate AppService");
        new Thread(){
            @Override
            public void run() {
                runing = true;
                while (runing){
                    Log.e(TAG,data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }


    @Override
    public void onDestroy() {
        runing=false;
        super.onDestroy();
        Log.e(TAG, "onDestroy AppService");
    }
}
