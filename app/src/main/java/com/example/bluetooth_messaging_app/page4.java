//package com.example.bluetooth_messaging_app;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.net.URI;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class page4 extends AppCompatActivity {
//
//    private Context context;
//    private CircleImageView ProfileImage;
//    private static final int PICK_IMAGE = 1;
//    private Button btn;
//    private static String LOG_TAG = "UIElementsPracticeLog";
//    private DBHelper dbHelper;
//    private TextView usr;
//    private String username;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_page4);
//
//        btn = findViewById(R.id.pick_profile_pic);
//        ProfileImage = findViewById(R.id.profile_pic);
//        context = this;
//
//        dbHelper = new DBHelper(context);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Pick Image"),PICK_IMAGE);
//            }
//        });
//
//        //test
//        dbHelper.initializeUser("HirushaUthsara",null);
//
//        // display username
//        username = dbHelper.getUserName();
//        usr = findViewById(R.id.ViewUserName);
//        usr.setText("Username : "+username);
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1){
//            Uri uri = data.getData();
//            ProfileImage.setImageURI(uri);
//        }
//
//    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        Log.i(LOG_TAG,"In Saved Instance State");
//        ProfileImage = findViewById(R.id.profile_pic);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//    }
//}
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
import android.widget.TextView;

public class page4 extends AppCompatActivity {

    String[] pictures = {"Profile Pic 1","Profile Pic 2","Profile Pic 3","Profile Pic 4","Profile Pic 5","Profile Pic 6"};

    AutoCompleteTextView autoCompleteText;
    ArrayAdapter<String> adapteritems;
    ImageView profilepic;
    Button updatebutton;
    String Username = "Ruchira"; //username should be taken from the database
    int profilepicture = 0; //profile picture should be taken from the database
    int currentpic = profilepicture;
    TextView usernamelabel;

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