package com.example.bluetooth_messaging_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;

import java.util.UUID;
 class ServerClass extends Thread
{
    private BluetoothServerSocket serverSocket;
    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING=2;
    static final int STATE_CONNECTED=3;
    static final int STATE_CONNECTION_FAILED=4;
    static final int STATE_MESSAGE_RECEIVED=5;

    String status , tempMsg;


    private static final String APP_NAME = "ChitChat";
    private static BluetoothDevice device;
    private static final UUID MY_UUID=UUID.fromString(device.getAddress());// if get any error use UUID

    BluetoothAdapter bluetoothAdapter=null;

    SendReceive sendReceive;



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
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what)
            {
                case STATE_LISTENING:
                    status="Listening";
                    break;
                case STATE_CONNECTING:
                    status= "Connecting";
                    break;
                case STATE_CONNECTED:
                    status= "Connected";
                    break;
                case STATE_CONNECTION_FAILED:
                    status= "Connection Failed";
                    break;
                case STATE_MESSAGE_RECEIVED:
                    byte[] readBuff= (byte[]) msg.obj;
                    tempMsg=new String(readBuff,0,msg.arg1);
                    //msg_box.setText(tempMsg);
                    break;
            }
            return true;
        }
    });
}
