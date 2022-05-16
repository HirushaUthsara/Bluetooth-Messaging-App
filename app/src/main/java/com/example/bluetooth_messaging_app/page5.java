package com.example.bluetooth_messaging_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class page5 extends AppCompatActivity {

    Button onlineusers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page5);

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