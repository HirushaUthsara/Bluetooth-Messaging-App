package com.example.bluetooth_messaging_app;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;

private class ServerClass extends Thread
{
    private BluetoothServerSocket serverSocket;

    public ServerClass(){
        try {
            BluetoothAdapter bluetoothAdapter;
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
