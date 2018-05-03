package com.example.leo.master.Message;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.leo.master.Main.Common;
import com.example.leo.master.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageChat extends AppCompatActivity {
    EditText mg_chatroom_message;
    Button mg_chatroom_send;
    RecyclerView mg_chatroom_rv;
    List<Message> messages;
    ScrollView mg_chatroom_scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_chatroom);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //設定聊天室標題
        String Name = getIntent().getStringExtra("Name");
        setTitle(Name);
        findviews();
        //設定有文字輸入才可以按button
        textchanged();
        messages = getMessages();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        mg_chatroom_rv.setLayoutManager(linearLayout);
        mg_chatroom_rv.setAdapter(new MessageAdapter(messages, this));
        mg_chatroom_scrollView.post(new Runnable() {
            @Override
            public void run() {
                mg_chatroom_scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });


//        messages.addAll(receive);
//        messages.addAll(send);

    }


    private void findviews() {
        int width = getResources().getDisplayMetrics().widthPixels;
        mg_chatroom_scrollView = findViewById(R.id.mg_chatroom_sv);
        mg_chatroom_rv = findViewById(R.id.mg_chatroom_rv);
        mg_chatroom_message = findViewById(R.id.mg_chatroom_message);
        mg_chatroom_send = findViewById(R.id.mg_chatroom_send);
        mg_chatroom_message.setWidth(width * 3 / 4);
//        mg_chatroom_send.setWidth(width / 4);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }
        return true;
    }




    private void textchanged() {
        mg_chatroom_message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String message = mg_chatroom_message.getText().toString();
                if (message.trim().isEmpty()) {
                    mg_chatroom_send.setEnabled(false);
                } else {
                    mg_chatroom_send.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void clickSend(View view) {
        String input = mg_chatroom_message.getText().toString();
        Date date = new Date();
        messages.add(new Message(Common.user_id, input, "date", 0));
        mg_chatroom_rv.getAdapter().notifyDataSetChanged();
        mg_chatroom_scrollView.post(new Runnable() {
            @Override
            public void run() {
                mg_chatroom_scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }



    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();

        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 0));
        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 0));
        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
//        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 0));
//        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
//        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
//        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 1));
//        messages.add(new Message("邊緣人", "安安你好嗎？", "18:00", 0));
        return messages;
    }


}