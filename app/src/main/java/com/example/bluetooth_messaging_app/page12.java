package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class page12 extends AppCompatActivity {
//new
    String[] items = {"school","business","sports","friends","family"};

    AutoCompleteTextView autoCompleteText;
    ArrayAdapter<String> adapteritems;
    ImageView profilepic;
    Button createbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page12);

        autoCompleteText = findViewById(R.id.auto_completetext);
        profilepic = findViewById(R.id.profile_image);

        createbutton = findViewById(R.id.createbutton);
        createbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        adapteritems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteText.setAdapter(adapteritems);

        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch(item){
                    case "school":
                        profilepic.setImageResource(R.drawable.university);
                        break;
                    case "business":
                        profilepic.setImageResource(R.drawable.business);
                        break;
                    case "sports":
                        profilepic.setImageResource(R.drawable.sports);
                        break;
                    case "friends":
                        profilepic.setImageResource(R.drawable.friends);
                        break;
                    case "family":
                        profilepic.setImageResource(R.drawable.family);
                        break;
                    default:
                        profilepic.setImageResource(R.drawable.group_def);
                        break;
                }

            }

        });


    }
}