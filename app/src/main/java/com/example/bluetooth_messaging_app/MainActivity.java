package com.example.bluetooth_messaging_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bluetooth_messaging_app.Adapters.FragmentsAdapter;
import com.example.bluetooth_messaging_app.Models.Users;
import com.example.bluetooth_messaging_app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewPager);

    }
    // link for page 13 setting
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_page14, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:
                Intent i = new Intent(getApplicationContext(),page4.class);
                startActivity(i);
                break;
            case R.id.search:
                Intent i1 = new Intent(getApplicationContext(),list_paired_devices.class);
                startActivity(i1);
                break;
            case R.id.create_groupchat:
                Intent i3 = new Intent(getApplicationContext(),page12.class);
                startActivity(i3);
                break;
            case R.id.bluetooth:
                Intent i4 = new Intent(getApplicationContext(),allcomponents.class);//page2
                startActivity(i4);
        }

        return super.onOptionsItemSelected(item);


    }
}