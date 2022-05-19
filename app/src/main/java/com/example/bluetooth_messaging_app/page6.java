package com.example.bluetooth_messaging_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bluetooth_messaging_app.Adapters.ChatAdapter;
import com.example.bluetooth_messaging_app.databinding.ActivityPage6Binding;

import java.util.ArrayList;
import java.util.Calendar;

public class page6 extends AppCompatActivity {
//new
    ActivityPage6Binding binding;
    Context context = this;
    String MyUserID; //This should be taken
    ImageView profilepicture;
    ArrayList<Message> messageModel = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        DBHelper db = new DBHelper(context);
        MyUserID = db.getUserID();

        binding = ActivityPage6Binding.inflate(getLayoutInflater());

        String userID = getIntent().getStringExtra("userID");
        String username = getIntent().getStringExtra("username");
        int Profilepic = getIntent().getIntExtra("profilepic",0);

        setContentView(binding.getRoot());

        profilepicture = findViewById(R.id.profilepic);
        switch(Profilepic){
            case 1: profilepicture.setImageResource(R.drawable.pic1);break;
            case 2: profilepicture.setImageResource(R.drawable.pic2);break;
            case 3: profilepicture.setImageResource(R.drawable.pic3);break;
            case 4: profilepicture.setImageResource(R.drawable.pic4);break;
            case 5: profilepicture.setImageResource(R.drawable.pic5);break;
            case 6: profilepicture.setImageResource(R.drawable.pic6);break;
            default: profilepicture.setImageResource(R.drawable.pic0);break;
        }

        binding.contactname.setText(username);
        binding.contactname.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),page7.class);
                startActivity(i1);
            }
        });

        binding.invitebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),page8.class);
                startActivity(i1);
            }
        });

        //I should take messages of the particular chat by the database using a function
        //messageModel=db.loadMessages(userID);
        Message m1 = new Message(1000,"B",MyUserID,"hi");
        Message m2 = new Message(1001,MyUserID,"B","hello");
        Message m3 = new Message(1015,"B",MyUserID,"How are you");
        Message m4 = new Message(1018,MyUserID,"B","I'm fine");
        Message m5 = new Message(1035,"B",MyUserID,"Where are u going today");
        Message m6 = new Message(1041,MyUserID,"B","I'm going to my sister's school. Wy is that?");
        Message m7 = new Message(1058,"B",MyUserID,"Can i joion with you?");
        Message m8 = new Message(1105,"B",MyUserID,"If you can' come with me");

        messageModel.add(m1);
        messageModel.add(m2);
        messageModel.add(m3);
        messageModel.add(m4);
        messageModel.add(m5);
        messageModel.add(m6);
        messageModel.add(m7);
        messageModel.add(m8);

        final ChatAdapter chatAdapter = new ChatAdapter(messageModel,this,MyUserID);
        binding.directchatmessages.setAdapter(chatAdapter);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        binding.directchatmessages.setLayoutManager(layoutmanager);

        binding.sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.typingtext.getText().toString();
                int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int min = Calendar.getInstance().get(Calendar.MINUTE);
                long current_time = (hour*100)+min;
                final Message m = new Message(current_time,MyUserID,userID,msg);
                messageModel.add(m);
                db.storeMessage(m);
                binding.typingtext.setText("");
            }
        });

    }


}
