package com.zhengsonglan.anotherapp;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    Button bt_start, bt_stop;
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
    }

    private void initView() {
        bt_start = (Button) findViewById(R.id.main_bt_start);
        bt_stop = (Button) findViewById(R.id.main_bt_stop);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_bt_start:
                startService(serviceIntent);
                Toast.makeText(this,"服务启动",Toast.LENGTH_SHORT).show();
                break;

            case R.id.main_bt_stop:
                stopService(serviceIntent);
                Toast.makeText(this,"服务关闭",Toast.LENGTH_SHORT).show();
                break;

        }
    }

}
