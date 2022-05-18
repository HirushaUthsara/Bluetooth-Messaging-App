package com.example.bluetooth_messaging_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page5 extends AppCompatActivity {

    Button onlineusers;
    TextView contactname;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        String userID = getIntent().getStringExtra("userID");
        String username = getIntent().getStringExtra("username");
        int profilepic = getIntent().getIntExtra("profilepic",0);

        setContentView(R.layout.activity_page5);

        image = findViewById(R.id.profilepic);
        switch(profilepic){
            case 1: image.setImageResource(R.drawable.university);break;
            case 2: image.setImageResource(R.drawable.business);break;
            case 3: image.setImageResource(R.drawable.sports);break;
            case 4: image.setImageResource(R.drawable.friends);break;
            case 5: image.setImageResource(R.drawable.family);break;
            default: image.setImageResource(R.drawable.pic0);break;
        }

        contactname = findViewById(R.id.contactname);
        contactname.setText(username);

        onlineusers = findViewById(R.id.onlineusersbutton);
        onlineusers.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page11.class);
                startActivity(i);
            }
        });

    }
}