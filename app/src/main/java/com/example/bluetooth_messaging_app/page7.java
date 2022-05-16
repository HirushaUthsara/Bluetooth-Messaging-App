package com.example.bluetooth_messaging_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class page7 extends AppCompatActivity {

    Button requestbutton1,requestbutton2,requestbutton3,requestbutton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page7);

        requestbutton1 = findViewById(R.id.requestbutton1);
        requestbutton1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page9.class);
                startActivity(i);
            }
        });

        requestbutton2 = findViewById(R.id.requestbutton2);
        requestbutton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page9.class);
                startActivity(i);
            }
        });

        requestbutton3 = findViewById(R.id.requestbutton3);
        requestbutton3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page9.class);
                startActivity(i);
            }
        });

        requestbutton4 = findViewById(R.id.requestbutton4);
        requestbutton4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),page9.class);
                startActivity(i);
            }
        });

    }

}