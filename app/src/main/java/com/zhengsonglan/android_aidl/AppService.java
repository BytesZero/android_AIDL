package com.zhengsonglan.android_aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class AppService extends Service {
    String TAG=this.getClass().getSimpleName();
    public AppService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG,"onBind AppService");
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"onCreate AppService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG,"onStart AppService");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy AppService");
    }
}
