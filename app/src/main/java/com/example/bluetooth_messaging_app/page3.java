package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

public class page3 extends AppCompatActivity {

    Button directchats,groupchat1,groupchat2_ADM,groupchat3,groupchat4_ADM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        directchats = findViewById(R.id.directchats);
        directchats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i2);
            }
        });

        groupchat1 = findViewById(R.id.groupchat1);
        groupchat1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(),page10.class);
                startActivity(i2);
            }
        });

        groupchat2_ADM = findViewById(R.id.groupchat2_ADM);
        groupchat2_ADM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page5.class);
                startActivity(i);
            }
        });


        groupchat3 = findViewById(R.id.groupchat3);
        groupchat3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(),page10.class);
                startActivity(i2);
            }
        });

        groupchat4_ADM = findViewById(R.id.groupchat4_ADM);
        groupchat4_ADM.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page5.class);
                startActivity(i);
            }
        });


    }
    // link for page 14 setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_page14, menu);
        return true;
    }
}