package com.example.bluetooth_messaging_app;//package com.example.bluetooth_messaging_app;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class page2_3 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_page2_3);
//    }
//}

import android.app.Activity;
        import android.bluetooth.BluetoothAdapter;
        import android.bluetooth.BluetoothDevice;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;

        import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class page2_3 extends AppCompatActivity {


    private static final int REQUEST_ENABLE_BT = 1;
    private ArrayAdapter<Object> adapter;
    private ListView listView;
    private BluetoothAdapter bluetoothAdapter;
    private ProgressBar progressBar;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_page2_3);
//    }
    private final BroadcastReceiver receiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();


            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                listView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);

// or
// You can just display Progress Dialog

            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {

                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

// Get th BluetoothDevice object from the Intent
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

// Add the name and address to array adapter
               adapter.add(bluetoothDevice.getName() + "\n" + bluetoothDevice.getAddress());
                //adapter.add(bluetoothDevice.getName());
                        System.out.println("------------------------------------------"+bluetoothDevice.getName());

            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

                listView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);

                adapter.add("No Device Found");

            }

            listView.setAdapter(adapter);


        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

// permission granted
// Register the Broadcast Receiver
                    IntentFilter filter = new IntentFilter();
                    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                    filter.addAction(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(receiver1, filter);

// Start discovering the nearby bluetooth devices
                    bluetoothAdapter.startDiscovery();

                } else {
// permission denied
                    Toast.makeText(this, "You cannot Find nearby device without permission", Toast.LENGTH_SHORT).show();
                }
        }
        return;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2_3);

        adapter = new ArrayAdapter<>(this, R.layout.list_items);

        listView = findViewById(R.id.listView);

        progressBar = findViewById(R.id.progressBar2);

        Button button = findViewById(R.id.button);


        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {

// Device not support BlueTooth
            Toast.makeText(this, "Device not support Bluetooth", Toast.LENGTH_SHORT).show();


        } else {


            Toast.makeText(this, "Device support Bluetooth", Toast.LENGTH_SHORT).show();

        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!bluetoothAdapter.isEnabled()) {

// Bluetooth not enabled yet , Request to enable
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);


                }


// check permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {

// permission granted
// continue the action

// Register the Broadcast Receiver
                    IntentFilter filter = new IntentFilter();
                    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
                    filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
                    filter.addAction(BluetoothDevice.ACTION_FOUND);
                    registerReceiver(receiver1, filter);


// Start discovering the nearby bluetooth devices
                    bluetoothAdapter.startDiscovery();


                } else {
// permission not granted
// ask for the permission
                    ActivityCompat.requestPermissions(page2_3.this, new String[]{ACCESS_COARSE_LOCATION}, 1);

                }

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {

// Bluetooth enabled

            } else {

// Bluetooth not enabled

            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

// Unregister broadcast receiver
        try {
            this.unregisterReceiver(receiver1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        bluetoothAdapter.cancelDiscovery();
    }


}