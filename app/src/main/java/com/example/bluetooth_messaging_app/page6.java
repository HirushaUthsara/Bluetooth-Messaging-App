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

public class page6 extends AppCompatActivity {

    ActivityPage6Binding binding;
    Context context = this;
    DBHelper db = new DBHelper(context);
    String MyUserID = db.getUserID(); //This should be taken
    ImageView profilepicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

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

        final ArrayList<Message> messageModel = new ArrayList<>();

        //I should take messages of the particular chat by the database using a function

        Message m1 = new Message(1,1200,"B","C","hi");
        Message m2 = new Message(3,1300,"A","A","h");
        Message m3 = new Message(2,1200,"B","A","hi");
        messageModel.add(m1);
        messageModel.add(m2);
        messageModel.add(m3);

        final ChatAdapter chatAdapter = new ChatAdapter(messageModel,this);
        binding.directchatmessages.setAdapter(chatAdapter);

        LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
        binding.directchatmessages.setLayoutManager(layoutmanager);

        binding.sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = binding.typingtext.getText().toString();
                final Message m = new Message(1,1,MyUserID,userID,msg);
                messageModel.add(m);
                binding.typingtext.setText("");
            }
        });

    }


}
