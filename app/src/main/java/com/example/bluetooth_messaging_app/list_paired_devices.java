package com.example.bluetooth_messaging_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Set;
import android.os.Bundle;

public class list_paired_devices extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter bluetoothAdapter;

    private ListView lstvw;
    private ArrayAdapter aAdapter;
    private BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_paired_devices);
        Button btn = (Button) findViewById(R.id.btnGet);

        Button btOn = findViewById(R.id.btn_on);
        Button btOff = findViewById(R.id.btn_off);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bAdapter == null) {
                    Toast.makeText(getApplicationContext(), "Bluetooth Not Supported", Toast.LENGTH_SHORT).show();
                } else {
                    Set<BluetoothDevice> pairedDevices = bAdapter.getBondedDevices();
                    ArrayList list = new ArrayList();
                    if (pairedDevices.size() > 0) {
                        for (BluetoothDevice device : pairedDevices) {
                            String devicename = device.getName();
                            String macAddress = device.getAddress();
                            list.add("   Name: " + devicename + "\n   MAC Address: " + macAddress);
                        }
                        lstvw = (ListView) findViewById(R.id.deviceList);
                        aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                        lstvw.setAdapter(aAdapter);
                    }
                }
            }
        });

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
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
    }
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