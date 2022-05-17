package com.example.bluetooth_messaging_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class page6 extends AppCompatActivity {

    Button contactbutton,invitebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        String userID = getIntent().getStringExtra("userID");
        String username = getIntent().getStringExtra("username");
        String profilepic = getIntent().getStringExtra("profilepic");

        setContentView(R.layout.activity_page6);

        contactbutton = findViewById(R.id.contactname);
        contactbutton.setText(username);
        contactbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),page7.class);
                startActivity(i1);
            }
        });

        invitebutton = findViewById(R.id.invitebutton);
        invitebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),page8.class);
                startActivity(i1);
            }
        });

    }


}
