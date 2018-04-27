package com.example.leo.master.Main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
}