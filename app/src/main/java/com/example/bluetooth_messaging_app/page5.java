package com.example.bluetooth_messaging_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page5 extends AppCompatActivity {

    Button onlineusers;
    TextView contactname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        String userID = getIntent().getStringExtra("userID");
        String username = getIntent().getStringExtra("username");
        String profilepic = getIntent().getStringExtra("profilepic");

        setContentView(R.layout.activity_page5);

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