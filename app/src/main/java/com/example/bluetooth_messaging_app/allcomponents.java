package com.example.bluetooth_messaging_app;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class allcomponents extends AppCompatActivity {
//New comment
    Button listen,send, listDevices;
    ListView listView;
    TextView msg_box,status;
    EditText writeMsg;

    BluetoothAdapter bluetoothAdapter;
    BluetoothDevice[] btArray;

    SendReceive sendReceive;

    String sending_msg;
    String receiving_msg;

    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING=2;
    static final int STATE_CONNECTED=3;
    static final int STATE_CONNECTION_FAILED=4;
    static final int STATE_MESSAGE_RECEIVED=5;

    int REQUEST_ENABLE_BLUETOOTH=1;

    private static final String APP_NAME = "BTChat";
    private static final UUID MY_UUID=UUID.fromString("8ce255c0-223a-11e0-ac64-0803450c9a66");

    public String getSending_msg() {
        return sending_msg;
    }

    public String getReceiving_msg() {
        return receiving_msg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcomponents);

        listen = (Button) findViewById(R.id.listen);
        send = (Button) findViewById(R.id.send);
        listView = (ListView) findViewById(R.id.listview);
        msg_box = (TextView) findViewById(R.id.msg);
        status = (TextView) findViewById(R.id.status);
        writeMsg = (EditText) findViewById(R.id.writemsg);
        listDevices = (Button) findViewById(R.id.listDevices);

        bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();

        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE_BLUETOOTH);
        }

        implementListeners();
    }



    //--------------------------------------------------------------------------
    private void implementListeners() {

        listDevices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<BluetoothDevice> bt=bluetoothAdapter.getBondedDevices();
                String[] strings=new String[bt.size()];
                btArray=new BluetoothDevice[bt.size()];
                int index=0;

                if( bt.size()>0)
                {
                    for(BluetoothDevice device : bt)
                    {
                        btArray[index]= device;
                        strings[index]=device.getName();
                        index++;
                    }
                    ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,strings);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });

        listen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServerClass serverClass=new ServerClass();
                serverClass.start();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ClientClass clientClass=new ClientClass(btArray[i]);
                clientClass.start();

                status.setText("Connecting");
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string= String.valueOf(writeMsg.getText());
                sendReceive.write(string.getBytes());
            }
        });
    }
    //--------------------------------------------------------------------------
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what)
            {
                case STATE_LISTENING:
                    status.setText("Listening");
                    break;
                case STATE_CONNECTING:
                    status.setText("Connecting");
                    break;
                case STATE_CONNECTED:
                    status.setText("Connected");
                    break;
                case STATE_CONNECTION_FAILED:
                    status.setText("Connection Failed");
                    break;
                case STATE_MESSAGE_RECEIVED:
                    byte[] readBuff= (byte[]) msg.obj;
                    String tempMsg=new String(readBuff,0,msg.arg1);
                    msg_box.setText(tempMsg);
                    sending_msg = tempMsg;
                    System.out.println("sending====---------------------------------------------------------------"+tempMsg);
                    break;
            }
            return true;
        }
    });
    //----------------------------------------------------------------------
    private void findViewByIdes() {
        listen=(Button) findViewById(R.id.listen);
        send=(Button) findViewById(R.id.send);
        listView=(ListView) findViewById(R.id.listview);
        msg_box =(TextView) findViewById(R.id.msg);
        status=(TextView) findViewById(R.id.status);
        writeMsg=(EditText) findViewById(R.id.writemsg);
        listDevices=(Button) findViewById(R.id.listDevices);
    }
    //----------------------------------------------------------------------
    private class ServerClass extends Thread
    {
        private BluetoothServerSocket serverSocket;

        public ServerClass(){
            try {
                serverSocket=bluetoothAdapter.listenUsingRfcommWithServiceRecord(APP_NAME,MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run()
        {
            BluetoothSocket socket=null;

            while (socket==null)
            {
                try {
                    Message message=Message.obtain();
                    message.what=STATE_CONNECTING;
                    handler.sendMessage(message);

                    socket=serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message=Message.obtain();
                    message.what=STATE_CONNECTION_FAILED;
                    handler.sendMessage(message);
                }

                if(socket!=null)
                {
                    Message message=Message.obtain();
                    message.what=STATE_CONNECTED;
                    handler.sendMessage(message);

                    sendReceive=new SendReceive(socket);
                    sendReceive.start();

                    break;
                }
            }
        }
    }
    //-----------------------------------------------------------------
    private class ClientClass extends Thread
    {
        private BluetoothDevice device;
        private BluetoothSocket socket;

        public ClientClass (BluetoothDevice device1)
        {
            device=device1;

            try {
                socket=device.createRfcommSocketToServiceRecord(MY_UUID);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run()
        {
            try {
                socket.connect();
                Message message=Message.obtain();
                message.what=STATE_CONNECTED;
                handler.sendMessage(message);

                sendReceive=new SendReceive(socket);
                sendReceive.start();

            } catch (IOException e) {
                e.printStackTrace();
                Message message=Message.obtain();
                message.what=STATE_CONNECTION_FAILED;
                handler.sendMessage(message);
            }
        }
    }
    //-------------------------------------------------------------------
    private class SendReceive extends Thread
    {
        private final BluetoothSocket bluetoothSocket;
        private final InputStream inputStream;
        private final OutputStream outputStream;

        public SendReceive (BluetoothSocket socket)
        {
            bluetoothSocket=socket;
            InputStream tempIn=null;
            OutputStream tempOut=null;

            try {
                tempIn=bluetoothSocket.getInputStream();
                tempOut=bluetoothSocket.getOutputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            inputStream=tempIn;
            outputStream=tempOut;
        }

        public void run()
        {
            byte[] buffer=new byte[1024];
            int bytes;

            while (true)
            {
                try {
                    bytes=inputStream.read(buffer);
                    //sending_msg =new String(buffer);
                    //System.out.println("sending====---------------------------------------------------------------"+sending_msg);

                    handler.obtainMessage(STATE_MESSAGE_RECEIVED,bytes,-1,buffer).sendToTarget();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public void write(byte[] bytes)
        {
            try {
                outputStream.write(bytes);
                receiving_msg=new String(bytes);
                System.out.println("receive====-----------------------------------------------------------------------"+receiving_msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //---------------------------------------------------------------------
}


//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.annotation.SuppressLint;
//import android.bluetooth.BluetoothAdapter;
//import android.bluetooth.BluetoothDevice;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Message;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.TextView;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Set;
//import java.util.UUID;
//
//public class allcomponents extends AppCompatActivity {
//
//    Button listen,send, listDevices;
//    ListView listView;
//    TextView msg_box,status;
//    EditText writeMsg;
//
//    static final int STATE_LISTENING = 1;
//    static final int STATE_CONNECTING=2;
//    static final int STATE_CONNECTED=3;
//    static final int STATE_CONNECTION_FAILED=4;
//    static final int STATE_MESSAGE_RECEIVED=5;
//
//
//
//
//    BluetoothAdapter bluetoothAdapter;
//    BluetoothDevice[] btArray;
//
//    int REQUEST_ENABLE_BLUETOOTH=1;
//
//    SendReceive sendReceive;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_allcomponents);
//
//        listen = (Button) findViewById(R.id.listen);
//        send = (Button) findViewById(R.id.send);
//        listView = (ListView) findViewById(R.id.listview);
//        msg_box = (TextView) findViewById(R.id.msg);
//        status = (TextView) findViewById(R.id.status);
//        writeMsg = (EditText) findViewById(R.id.writemsg);
//        listDevices = (Button) findViewById(R.id.listDevices);
//        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//
//        if (!bluetoothAdapter.isEnabled()) {
//            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(enableIntent, REQUEST_ENABLE_BLUETOOTH);
//        }
//
//        listDevices.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Set<BluetoothDevice> bt = bluetoothAdapter.getBondedDevices();
//                String[] strings = new String[bt.size()];
//                btArray = new BluetoothDevice[bt.size()];
//                int index = 0;
//
//                if (bt.size() > 0) {
//                    for (BluetoothDevice device : bt) {
//                        btArray[index] = device;
//                        strings[index] = device.getName();
//                        System.out.println("device name is "+device.getName()+"<-----------------------------------------");
//                        index++;
//                    }
//                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
//                    listView.setAdapter(arrayAdapter);
//                }
//            }
//        });
////
////        listen.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                ServerClass serverClass = new ServerClass();
////                serverClass.start();
////            }
////        });
////
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                System.out.println("click the list view <---------------------------------------------------");
//                ClientClass clientClass = new ClientClass(btArray[i]);
//                clientClass.start();
//
//                //status.setText("Connecting");
//            }
//        });
////
////        send.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                String string = String.valueOf(writeMsg.getText());
////                sendReceive.write(string.getBytes());
////            }
////        });
//    }

//}