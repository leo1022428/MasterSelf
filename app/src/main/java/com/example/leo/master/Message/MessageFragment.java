package com.example.leo.master.Message;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.leo.master.Main.Common;
import com.example.leo.master.Main.MyTask;
import com.example.leo.master.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MessageFragment extends Fragment {
    MyTask getAllChatroomTask;
    private final static String TAG = "Message";
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Message");
        //啟用message_fragment
        View view = inflater.inflate(R.layout.message_frag, container, false);
        //取得message_recycerview 需要的資料
        List<Message> items = getitems();
        //初始化message_recycerview
        RecyclerView mg_rv = view.findViewById(R.id.mg_recyclerview);
        //設定message_recycerview layout類型
        mg_rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        //載入message_recycerview adapter
        mg_rv.setAdapter(new mg_rv_adapter(items, getActivity()));
        return view;

    }


    private class mg_rv_adapter extends RecyclerView.Adapter<mg_rv_adapter.mg_rv_viewholder> {
        List<Message> items;
        Context context;
        //message_recycerview的position為0時,顯示message_title
        static final int TYPE_TITLE = 0;
        //message_recycerview的position為1時,顯示message_rv的內容
        static final int TYPE_ITEM = 1;

        public mg_rv_adapter(List<Message> items, Context context) {
            this.items = items;
            this.context = context;
        }

        public class mg_rv_viewholder extends RecyclerView.ViewHolder {
            //初始化view中的元件
            ImageView item_picture;
            TextView item_time, item_content, item_name;

            public mg_rv_viewholder(View itemView) {
                super(itemView);
                item_picture = itemView.findViewById(R.id.mg_itemview_picture);
                item_time = itemView.findViewById(R.id.mg_itemview_time);
                item_content = itemView.findViewById(R.id.mg_itemview_content);
                item_name = itemView.findViewById(R.id.mg_itemview_name);

            }
        }

        @Override
        public int getItemCount() {
            //position為0時為title, recyclerview內容從1開始顯示, itemcount需往後加1
            return items.size() + 1;
        }


        //定義recyclerview的viewtype,position等於0顯示title,其他為recyclerview內容
        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_TITLE;
            } else {
                return TYPE_ITEM;
            }

        }

        @Override
        public mg_rv_viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View itemview;
            //判斷recyclerview的viewType
            if (viewType == TYPE_TITLE) {
                itemview = layoutInflater.inflate(R.layout.message_rv_title, parent, false);
            } else {
                itemview = layoutInflater.inflate(R.layout.message_rv_item, parent, false);
            }

            return new mg_rv_viewholder(itemview);
        }

        @Override
        public void onBindViewHolder(final mg_rv_viewholder viewholder, int position) {
            if (position == 0) {

            } else {
                final String chatName;
                //將對應的position資料塞入view中
//                final Chatroom chatroom = items.get(position - 1);
//                if (chatroom.getUser_id_a() == Common.user_id) {
//                    chatName = chatroom.getUser_id_b();
//                    viewholder.item_name.setText(chatName);
//                } else {
//                    chatName = chatroom.getUser_id_a();
//                    viewholder.item_name.setText(chatName);
//                }
                final Message item = items.get(position -1);
                viewholder.item_picture.setImageResource(R.drawable.picture);
                viewholder.item_time.setText(item.getTime());
                viewholder.item_content.setText(item.getContent());
                viewholder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, MessageChatroom.class);
                        intent.putExtra("Name", item.getName());
                        startActivity(intent);

                    }
                });

            }

        }


    }


    //從server抓資料
    private List<Message> getitems() {
//        List<Chatroom> chatrooms = new ArrayList<>();
//        if (Common.networkConnected(getActivity())) {
//            JsonObject jsonObject = new JsonObject();
//            jsonObject.addProperty("action", "getAllChatroom");
//            jsonObject.addProperty("user_id", Common.user_id);
//            getAllChatroomTask = new MyTask(Common.URL + "/MessageServlet", jsonObject.toString());
//            try {
//                String jsonIn = getAllChatroomTask.execute().get();
//                Type listtype = new TypeToken<List<Chatroom>>() {
//                }.getType();
//                chatrooms = gson.fromJson(jsonIn, listtype);
//
//            } catch (Exception e) {
//                Log.d(TAG, "Error : " + e.toString());
//            }
//
//            if (chatrooms == null || chatrooms.isEmpty()) {
//                Toast.makeText(getActivity(), R.string.NoNotifications, Toast.LENGTH_SHORT);
//
//            }
//
//        } else {
//            Toast.makeText(getActivity(), R.string.NoConnection, Toast.LENGTH_SHORT);
//        }
//        return chatrooms;
//    }
        List<Message> items = new ArrayList<>();
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("邊緣人", "你好你在嗎,可以跟你當朋友嗎？", R.drawable.picture, "10:24 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("工具人", "你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎你在洗澡嗎", R.drawable.picture, "10:50 PM"));
        items.add(new Message("外國人", "Could I FXXK U", R.drawable.picture, "4:09 AM"));
        return items;}
}



