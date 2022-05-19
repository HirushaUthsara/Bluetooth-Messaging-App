package com.example.bluetooth_messaging_app.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluetooth_messaging_app.Models.Users;
import com.example.bluetooth_messaging_app.R;
import com.example.bluetooth_messaging_app.page5;
import com.example.bluetooth_messaging_app.page6;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{
//This holds the chat rows of the two main layouts

    ArrayList<Users> list;
    Context context;
    int DorC;

    public UserAdapter(int DorC,ArrayList<Users> list, Context context) {   //Constructor
        this.DorC = DorC;   //This is used for identify the whether this is direct chat or a group
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {     //Creating the row in the main layout
        View view = LayoutInflater.from(context).inflate(R.layout.activity_chatrow, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Users users = list.get(position);   //Getting the position of the chat in the main chat list
        if(DorC==0) {
            switch (users.getProfilepicture()) {    //Selecting the profile picture of the direct chat
                case 1:
                    holder.image.setImageResource(R.drawable.pic1);
                    break;
                case 2:
                    holder.image.setImageResource(R.drawable.pic2);
                    break;
                case 3:
                    holder.image.setImageResource(R.drawable.pic3);
                    break;
                case 4:
                    holder.image.setImageResource(R.drawable.pic4);
                    break;
                case 5:
                    holder.image.setImageResource(R.drawable.pic5);
                    break;
                case 6:
                    holder.image.setImageResource(R.drawable.pic6);
                    break;
                default:
                    holder.image.setImageResource(R.drawable.pic0);
                    break;
            }
        }else{      //Selecting the profile picture of the group chat
            switch (users.getProfilepicture()){
                case 1:
                    holder.image.setImageResource(R.drawable.university);
                    break;
                case 2:
                    holder.image.setImageResource(R.drawable.business);
                    break;
                case 3:
                    holder.image.setImageResource(R.drawable.sports);
                    break;
                case 4:
                    holder.image.setImageResource(R.drawable.friends);
                    break;
                case 5:
                    holder.image.setImageResource(R.drawable.family);
                    break;
                default:
                    holder.image.setImageResource(R.drawable.group_def);
                    break;
            }
        }
        holder.username.setText(users.getUsername());   //set the name of the chat
        holder.lastmessage.setText(users.getLastmessage()); //Set the last message of the chat

        holder.itemView.setOnClickListener(new View.OnClickListener() { //When chat is clicked
            @Override
            public void onClick(View view) {
                if(DorC==0) {   //Sending data to the chat activity of the direct chat
                    Intent i = new Intent(context, page6.class);
                    i.putExtra("userID", users.getUserID());
                    i.putExtra("username", users.getUsername());
                    i.putExtra("profilepic", users.getProfilepicture());
                    context.startActivity(i);
                }else if(DorC==1){  //Sending data to the chat activity of the group chat
                    Intent i = new Intent(context, page5.class);
                    i.putExtra("userID", users.getUserID());
                    i.putExtra("username", users.getUsername());
                    i.putExtra("profilepic", users.getProfilepicture());
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    //This class holds the layout of the chat row
        ImageView image;
        TextView username, lastmessage;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            image = itemView.findViewById(R.id.profile_image1);
            username = itemView.findViewById(R.id.Contactname);
            lastmessage = itemView.findViewById(R.id.Lastmessage1);

        }

    }
}
