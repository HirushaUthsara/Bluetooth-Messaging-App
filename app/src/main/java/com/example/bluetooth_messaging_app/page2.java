package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;

public class page2 extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
    }

    Button btOn = findViewById(R.id.bt_on);
    Button btOff = findViewById(R.id.bt_off);


    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    if (bluetoothAdapter == null) {

// Device not support BlueTooth
        Toast.makeText(this, "Device not support BlueTooth", Toast.LENGTH_SHORT).show();
        btOff.setEnabled(false);
        btOn.setEnabled(false);

    } else {

        Toast.makeText(this, "Device support BlueTooth", Toast.LENGTH_SHORT).show();

    }


    btOn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (!bluetoothAdapter.isEnabled()) {

// Bluetooth not enabled yet , Request to enable
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


            } else {

// Bluetooth already enabled
                Toast.makeText(getApplicationContext(), "BlueTooth already enabled", Toast.LENGTH_SHORT).show();


            }


        }
    });
    btOff.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter.isEnabled()) {

// Bluetooth enabled , disable now
                bluetoothAdapter.disable();
                Toast.makeText(getApplicationContext(), "Bluetooth disabled", Toast.LENGTH_SHORT).show();


            } else {

// Bluetooth already disabled
                Toast.makeText(getApplicationContext(), "Bluetooth already disabled", Toast.LENGTH_SHORT).show();


            }


        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {

                Toast.makeText(getApplicationContext(), "Bluetooth enabled", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "Bluetooth not enabled", Toast.LENGTH_SHORT).show();

            }

        }
    }


}