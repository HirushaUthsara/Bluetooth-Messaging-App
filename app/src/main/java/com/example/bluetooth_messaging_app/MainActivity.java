package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    Button button_for_page2_direct_chat,button2page5,button2page6,button2page10,button2page11;
    Button button_for_page3_group_chat, button_for_page2_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hiiiiiii

        button2page5 = findViewById(R.id.button2page5);
        button2page5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(getApplicationContext(),page5.class);
                startActivity(i1);
            }
        });
        button2page6 = findViewById(R.id.button2page6);
        button2page6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(getApplicationContext(),page6.class);
                startActivity(i2);
            }
        });
        button2page10 = findViewById(R.id.button2page10);
        button2page10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(getApplicationContext(),page10.class);
                startActivity(i3);
            }
        });
        button2page11 = findViewById(R.id.button2page11);
        button2page11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i4 = new Intent(getApplicationContext(),page11.class);
                startActivity(i4);
            }
        });



        // link to page 2 direct chat
        button_for_page2_direct_chat = findViewById(R.id.button_for_page2_direct_chat);
        button_for_page2_direct_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page2.class);
                startActivity(i);
            }
        });

        // link to page 3 group chat
        button_for_page3_group_chat = findViewById(R.id.button_for_page3_group_chat);
        button_for_page3_group_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page3.class);
                startActivity(i);
            }
        });
        // link to page 3 group chat
        button_for_page2_3 = findViewById(R.id.button_for_page2_3);
        button_for_page2_3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page2_3.class);
                startActivity(i);
            }
        });

    }
    // link for page 13 setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_page13, menu);
        return true;
    }
}