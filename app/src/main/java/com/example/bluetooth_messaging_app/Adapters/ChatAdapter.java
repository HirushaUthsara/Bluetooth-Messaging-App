package com.example.bluetooth_messaging_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetooth_messaging_app.DBHelper;
import com.example.bluetooth_messaging_app.Message;
import com.example.bluetooth_messaging_app.R;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{



    ArrayList<Message> messageModel;
    Context context;
    String MyUserID;


    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<Message> messageModel,Context context,String MyUserID){
        this.messageModel = messageModel;
        this.context = context;
        this.MyUserID = MyUserID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sendingmsg,parent,false);
            return new SenderViewHolder(view);
        }else{
            View view = LayoutInflater.from(context).inflate(R.layout.receivingmsg,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position){
        if(messageModel.get(position).getSenderId().equals(MyUserID)){      //Our ID
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message messagemodel1 = messageModel.get(position);

        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messagemodel1.getContent());
            ((SenderViewHolder)holder).senderTime.setText(String.valueOf(messagemodel1.getTime()));
        }else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(messagemodel1.getContent());
            ((ReceiverViewHolder)holder).receiverTime.setText(String.valueOf(messagemodel1.getTime()));
        }
    }

    @Override
    public int getItemCount() {
        return messageModel.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        TextView receiverMsg, receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.ReceiverText);
            receiverTime = itemView.findViewById(R.id.ReceiverTime);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {

        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.SenderText);
            senderTime = itemView.findViewById(R.id.SenderTime);
        }
    }
}