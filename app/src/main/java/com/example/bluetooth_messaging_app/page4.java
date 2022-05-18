package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class page4 extends AppCompatActivity {

    String[] pictures = {"Profile Pic 1","Profile Pic 2","Profile Pic 3","Profile Pic 4","Profile Pic 5","Profile Pic 6"};

    AutoCompleteTextView autoCompleteText;
    ArrayAdapter<String> adapteritems;
    ImageView profilepic;
    Button updatebutton;
    TextView usernamelabel;
    String userid;
    Context context = this;
    DBHelper db = new DBHelper(context);
    String Username = db.getUserName(); //username should be taken from the database
    int profilepicture = db.getUserProfile(); //profile picture should be taken from the database
    int currentpic = profilepicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_page4);

        usernamelabel = findViewById(R.id.usernamelabel);
        usernamelabel.setText(Username);

        profilepic = findViewById(R.id.profile_image);

        switch(profilepicture){
            case 1: profilepic.setImageResource(R.drawable.pic1);break;
            case 2: profilepic.setImageResource(R.drawable.pic2);break;
            case 3: profilepic.setImageResource(R.drawable.pic3);break;
            case 4: profilepic.setImageResource(R.drawable.pic4);break;
            case 5: profilepic.setImageResource(R.drawable.pic5);break;
            case 6: profilepic.setImageResource(R.drawable.pic6);break;
            default: profilepic.setImageResource(R.drawable.pic0);break;
        }

        autoCompleteText = findViewById(R.id.auto_completetext);

        adapteritems = new ArrayAdapter<String>(this,R.layout.list_item,pictures);
        autoCompleteText.setAdapter(adapteritems);

        updatebutton = findViewById(R.id.updatebutton);
        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //You can use the 'currentpic' variable to update the profile picture
                userid = db.getUserID();
                db.setUserProfile(userid,currentpic);
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

        autoCompleteText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                switch(item){
                    case "Profile Pic 1":
                        profilepic.setImageResource(R.drawable.pic1);
                        currentpic = 1;
                        break;
                    case "Profile Pic 2":
                        profilepic.setImageResource(R.drawable.pic2);
                        currentpic = 2;
                        break;
                    case "Profile Pic 3":
                        profilepic.setImageResource(R.drawable.pic3);
                        currentpic = 3;
                        break;
                    case "Profile Pic 4":
                        profilepic.setImageResource(R.drawable.pic4);
                        currentpic = 4;
                        break;
                    case "Profile Pic 5":
                        profilepic.setImageResource(R.drawable.pic5);
                        currentpic = 5;
                        break;
                    case "Profile Pic 6":
                        profilepic.setImageResource(R.drawable.pic6);
                        currentpic = 6;
                        break;
                    default:
                        profilepic.setImageResource(R.drawable.pic0);
                        currentpic = 0;
                        break;
                }

            }

        });


    }
}