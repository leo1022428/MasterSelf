package com.example.leo.master.Main;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leo.master.Message.MessageFragment;
import com.example.leo.master.Notification.NotificationFragment;
import com.example.leo.master.Notification.NotificationReceiver;
import com.example.leo.master.R;

import javax.security.auth.PrivateCredentialPermission;

public class MainActivity extends AppCompatActivity {

    public static int TYPE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_main);
        switchfragment();
        Common.connectSocket(this);
        Intent intent = new Intent(this,MainService.class);
        startService(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment nf_fragment = new NotificationFragment();
        fragmentTransaction.replace(R.id.content, nf_fragment);
        fragmentTransaction.commit();
    }

    public void switchfragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (TYPE) {
            case 0:
                Fragment nf_fragment = new NotificationFragment();
                fragmentTransaction.replace(R.id.content, nf_fragment);
                fragmentTransaction.commit();
                break;
            case 1:
                Fragment ms_fragment = new MessageFragment();
                fragmentTransaction.replace(R.id.content, ms_fragment);
                fragmentTransaction.commit();
                break;

        }


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Common.disconnectSocket();
        Intent intent = new Intent(this,MainService.class);
        stopService(intent);
    }
}
