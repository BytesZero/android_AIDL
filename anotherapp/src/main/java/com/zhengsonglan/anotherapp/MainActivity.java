package com.zhengsonglan.anotherapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zhengsonglan.android_aidl.IMyAidlInterface;


public class MainActivity extends ActionBarActivity implements View.OnClickListener, ServiceConnection {
    String TAG = this.getClass().getSimpleName();

    Button bt_start, bt_stop, bt_bind, bt_unbind, bt_sync;
    EditText et_message;
    String appPackage = "com.zhengsonglan.android_aidl";
    String appService = "com.zhengsonglan.android_aidl.AppService";

    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        initData();

    }

    private void initData() {
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(appPackage, appService));
    }

    private void initEvent() {
        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
        bt_bind.setOnClickListener(this);
        bt_unbind.setOnClickListener(this);
        bt_sync.setOnClickListener(this);
    }

    private void initView() {
        bt_start = (Button) findViewById(R.id.main_bt_start);
        bt_stop = (Button) findViewById(R.id.main_bt_stop);
        bt_bind = (Button) findViewById(R.id.main_bt_bind);
        bt_unbind = (Button) findViewById(R.id.main_bt_unbind);
        bt_sync = (Button) findViewById(R.id.main_bt_sync);
        et_message = (EditText) findViewById(R.id.main_et_message);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_start:
                startService(serviceIntent);
                Toast.makeText(this, "服务启动", Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bt_stop:
                stopService(serviceIntent);
                Toast.makeText(this, "服务关闭", Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bt_bind:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                Toast.makeText(this, "服务绑定", Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bt_unbind:

                unbindService(this);
                binder = null;
                Toast.makeText(this, "服务解绑", Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bt_sync:
                if (binder != null) {
                    try {
                        binder.setData(et_message.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        Log.e(TAG, "onServiceConnected:" + service);

        binder = IMyAidlInterface.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        Log.e(TAG, "onServiceDisconnected:");
    }

    IMyAidlInterface binder;
}
