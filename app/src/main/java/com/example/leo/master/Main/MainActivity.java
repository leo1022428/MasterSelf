package com.example.leo.master.Main;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.leo.master.Message.MessageFragment;
import com.example.leo.master.Notification.NotificationFragment;
import com.example.leo.master.R;


public class MainActivity extends AppCompatActivity {

    public static int TYPE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.master_main);
        switchfragment();
        Intent intent = new Intent(this, MainService.class);
        startService(intent);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Toast.makeText(this,"newintent",Toast.LENGTH_SHORT);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment nf_fragment = new MessageFragment();
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
        Intent intent = new Intent(this, MainService.class);
        stopService(intent);

    }
}
