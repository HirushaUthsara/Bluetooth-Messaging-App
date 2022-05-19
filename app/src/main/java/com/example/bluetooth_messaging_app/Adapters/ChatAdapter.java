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
//This class holds the sending and receiving msg uis of the interface

    ArrayList<Message> messageModel;
    Context context;
    String MyUserID;


    int SENDER_VIEW_TYPE = 1;   //These variables are used to find whether this is a sending msg or a receivig msg
    int RECEIVER_VIEW_TYPE = 2; //These variables are used to find whether this is a sending msg or a receivig msg

    public ChatAdapter(ArrayList<Message> messageModel,Context context,String MyUserID){    //Constructor for the chat adapter
        this.messageModel = messageModel;
        this.context = context;
        this.MyUserID = MyUserID;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){ //If this is a sending msg assign a sender view holder
            View view = LayoutInflater.from(context).inflate(R.layout.sendingmsg,parent,false);
            return new SenderViewHolder(view);
        }else{  //If this is a receiving msg assign a receiver view holder
            View view = LayoutInflater.from(context).inflate(R.layout.receivingmsg,parent,false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position){
        if(messageModel.get(position).getSenderId().equals(MyUserID)){      //Finding whether msg is a sending one or a receiving one uing the sender's id
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message messagemodel1 = messageModel.get(position);
        //Showing the msg and the time of the msg
        if(holder.getClass()==SenderViewHolder.class){  //Of the sender
            ((SenderViewHolder)holder).senderMsg.setText(messagemodel1.getContent());
            ((SenderViewHolder)holder).senderTime.setText(String.valueOf(messagemodel1.getTime()));
        }else{      //Of the receiver
            ((ReceiverViewHolder)holder).receiverMsg.setText(messagemodel1.getContent());
            ((ReceiverViewHolder)holder).receiverTime.setText(String.valueOf(messagemodel1.getTime()));
        }
    }

    @Override
    public int getItemCount() {
        return messageModel.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
    //this class holds the receiving msg layout
        TextView receiverMsg, receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg = itemView.findViewById(R.id.ReceiverText);     //Receiving msg
            receiverTime = itemView.findViewById(R.id.ReceiverTime);    //receiving msg time
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
    //This class holds the sending msg layout
        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.SenderText);     //Sending msg
            senderTime = itemView.findViewById(R.id.SenderTime);    //Sending msg time
        }
    }
}