package com.example.leo.master.Message;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.leo.master.R;

public class MessageChatroom extends AppCompatActivity {
    EditText mg_chatroom_message;
    Button mg_chatroom_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chatroom);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        String Name = getIntent().getStringExtra("Name");
        setTitle(Name);


        int width = getResources().getDisplayMetrics().widthPixels;
        mg_chatroom_message = findViewById(R.id.mg_chatroom_message);
        mg_chatroom_send = findViewById(R.id.mg_chatroom_send);
        mg_chatroom_message.setWidth(width * 3 / 4);
        mg_chatroom_send.setWidth(width / 4);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}