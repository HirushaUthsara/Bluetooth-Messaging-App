package com.example.bluetooth_messaging_app;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.UUID;

private class ClientClass extends Thread
{
    private static final UUID MY_UUID = ;
    private static final Object STATE_CONNECTED = ;
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
