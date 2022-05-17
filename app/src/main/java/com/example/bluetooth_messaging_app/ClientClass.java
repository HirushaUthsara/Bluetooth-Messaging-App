package com.example.bluetooth_messaging_app;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

import android.os.Handler;
import android.os.Message;

class ClientClass extends Thread
{
    private static BluetoothDevice device;
    private static final UUID MY_UUID=UUID.fromString("467f91ca-d597-11ec-9d64-0242ac120002");
    static final int STATE_LISTENING = 1;
    static final int STATE_CONNECTING=2;
    static final int STATE_CONNECTED=3;
    static final int STATE_CONNECTION_FAILED=4;
    static final int STATE_MESSAGE_RECEIVED=5;
    private BluetoothSocket socket;

    private SendReceive sendReceive;

    private String status , tempMsg;

//    public String getStatus() {
//        return status;
//    }
//
//    public String getTempMsg() {
//        return tempMsg;
//    }

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
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

//            switch (msg.what)
//            {
//                case STATE_LISTENING:
//                    status="Listening";
//                    break;
//                case STATE_CONNECTING:
//                    status= "Connecting";
//                    break;
//                case STATE_CONNECTED:
//                    status= "Connected";
//                    break;
//                case STATE_CONNECTION_FAILED:
//                    status= "Connection Failed";
//                    break;
//                case STATE_MESSAGE_RECEIVED:
//                    byte[] readBuff= (byte[]) msg.obj;
//                    tempMsg=new String(readBuff,0,msg.arg1);
//                    //msg_box.setText(tempMsg);
//                    break;
//            }
            return true;
        }
    });
}
