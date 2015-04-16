package com.zhengsonglan.android_aidl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {
    Intent intentService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentService=new Intent(this, AppService.class);
        startService(intentService);

    }

    @Override
    protected void onDestroy() {
        stopService(intentService);
        super.onDestroy();
    }
}
