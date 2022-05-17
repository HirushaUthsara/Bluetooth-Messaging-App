package com.example.bluetooth_messaging_app;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URI;

import de.hdodenhof.circleimageview.CircleImageView;

public class page4 extends AppCompatActivity {

    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page4);

        btn = findViewById(R.id.pick_profile_pic);
        ProfileImage = findViewById(R.id.profile_pic);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Pick Image"),PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            Uri uri = data.getData();
            ProfileImage.setImageURI(uri);
            btn.setText("Profile Picture Changed");
        }

    }
}