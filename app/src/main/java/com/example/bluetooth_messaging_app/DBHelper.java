package com.example.bluetooth_messaging_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // tables of database constructed here
        // useId and contactID will be basically their Mac Address
        db.execSQL("create Table UserDetails (UserID TEXT primary key, username TEXT,user_profile_pic BLOB)");
        db.execSQL("create Table DirectContacts (ContactID TEXT primary key, username TEXT,contact_profile_pic BLOB)");
        db.execSQL("create Table Messages (MESSAGEID INTEGER primary key AUTOINCREMENT, TIME INTEGER,SENDERID INTEGER, RECEIVERID INTEGER, CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop tables to upgrade database then create again
        db.execSQL("drop Table if exists UserDetails");
        db.execSQL("drop Table if exists DirectContacts");
        db.execSQL("drop Table if exists Messages");
        onCreate(db);
    }


    // database operations

    public void storeMessage(Message msg){     // store the message object in database

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("TIME",msg.getTime());
        contentValues.put("SENEDERID",msg.getSenderId());
        contentValues.put("RECEIVERID",msg.getReceiverId());
        contentValues.put("CONTENT",msg.getContent());

        // save to table
        sqLiteDatabase.insert("Messages",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }


    public void storeContact(Contact cnt){     // store the Contact object in database

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ContactID",cnt.getId());
        contentValues.put("username",cnt.getUsername());
        contentValues.put("contact_profile_pic",(byte[]) null);

        // save to table
        sqLiteDatabase.insert("DirectContacts",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }


    public void initializeUser(String username, @Nullable Bitmap profile_pic){     // initialize user details

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("UserID",getMacAddress());
        contentValues.put("username",username);


        contentValues.put("user_profile_pic", (byte[]) null);

        // save to table
        sqLiteDatabase.insert("UserDetails",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    public static String getMacAddress() {
        try {
            ArrayList<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    String hex = Integer.toHexString(b & 0xFF);
                    if (hex.length() == 1)
                        hex = "0".concat(hex);
                    res1.append(hex.concat(":"));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }



}
