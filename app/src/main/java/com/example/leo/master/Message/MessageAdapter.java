package com.example.leo.master.Message;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leo.master.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {
    List<Message> messages;
    Context context;
    private final static int TYPE_SEND = 0;
    private final static int TYPE_receive = 1;
    Message message = null;

    public MessageAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mg_chatroom_receive_image;
        TextView mg_chatroom_receive_content, mg_chatroom_send_content, mg_chatroom_receive_time, mg_chatroom_send_time;

        public ViewHolder(View itemView) {
            super(itemView);
            mg_chatroom_send_content = itemView.findViewById(R.id.mg_chatroom_send_content);
            mg_chatroom_send_time = itemView.findViewById(R.id.mg_chatroom_send_time);
            mg_chatroom_receive_image = itemView.findViewById(R.id.mg_chatroom_receive_image);
            mg_chatroom_receive_content = itemView.findViewById(R.id.mg_chatroom_receive_content);
            mg_chatroom_receive_time = itemView.findViewById(R.id.mg_chatroom_receive_time);

        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    @Override
    public int getItemViewType(int position) {
        message = messages.get(position);
        if (message.getMessage_type() == 0) {
            return TYPE_SEND;
        } else {
            return TYPE_receive;
        }
    }

    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        if (viewType == TYPE_SEND) {
            itemView = layoutInflater.inflate(R.layout.message_chatroom_send, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.message_chatroom_receive, parent, false);
        }
        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder viewHolder, int position) {
        message = messages.get(position);
        if (message.getMessage_type() == 0){
            viewHolder.mg_chatroom_send_time.setText(message.getTime());
            viewHolder.mg_chatroom_send_content.setText(message.getContent());
        }else {
            viewHolder.mg_chatroom_receive_image.setImageResource(R.drawable.picture);
            viewHolder.mg_chatroom_receive_time.setText(message.getTime());
            viewHolder.mg_chatroom_receive_content.setText(message.getContent());
        }


    }

}




