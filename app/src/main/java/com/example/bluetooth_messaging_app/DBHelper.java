package com.example.bluetooth_messaging_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;


public class DBHelper extends SQLiteOpenHelper {

    private final ArrayList<Contact> allContacts = new ArrayList<>();
    private final ArrayList<Message> allMessages = new ArrayList<>();
    private Context context;

    public DBHelper(Context context) {
        super(context, "Userdata.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // tables of database constructed here
        // userId and contactID will be basically their Mac Address
        db.execSQL("create Table UserDetails (UserID TEXT primary key, username TEXT,user_profile_pic INTEGER)");
        db.execSQL("create Table DirectContacts (ContactID TEXT primary key, username TEXT,contact_profile_pic INTEGER)");
        db.execSQL("create Table Messages (MESSAGEID INTEGER primary key AUTOINCREMENT, TIME INTEGER,SENDERID TEXT, RECEIVERID TEXT, CONTENT TEXT)");
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

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

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

    public String getUserName(){
        String username = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            username = cursor.getString(1);
        }
        db.close();
        return username;
    }

    public int getUserProfile(){
        int profile_pic = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            profile_pic = cursor.getInt(2);
        }
        db.close();
        return profile_pic;
    }

    public void setUserProfile(String userid ,int pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_profile_pic",pic);

        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails Where UserID = ? ",new String[] {userid});

        long result = db.update("UserDetails",contentValues,"UserID = ?",new String[]{userid});

        db.close();

    }

    public String getUserID(){
        String userid = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            userid = cursor.getString(0);
        }
        db.close();
        return userid;
    }


    public void storeContact(Contact cnt){     // store the Contact object in database

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ContactID",cnt.getId());
        contentValues.put("username",cnt.getUsername());
        contentValues.put("contact_profile_pic",(byte[]) null);

        // save to table
        sqLiteDatabase.insert("DirectContacts",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }


    public void initializeUser(String username,int pic){     // initialize user details

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("UserID",getMacAddress());
        contentValues.put("username",username);


        contentValues.put("user_profile_pic", pic);

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

    public ArrayList<Contact> loadAllContacts(){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM  DirectContacts";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                Contact c = new Contact();
                c.setId(cursor.getString(0));
                c.setUsername(cursor.getString(1));
                c.setProfile_pic(cursor.getInt(2));

                allContacts.add(c);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return allContacts;
    }

    public ArrayList<Message> loadMessages(String contactId){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM  Messages Where "+ contactId +" =  SENDERID OR "+ contactId +" = RECEIVERID ORDER BY TIME";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            do {
                try {
                    Message msg = new Message();
                    msg.setId(cursor.getInt(0));
                    msg.setTime(cursor.getLong(1));
                    msg.setSenderId(cursor.getString(2));
                    msg.setReceiverId(cursor.getString(3));
                    msg.setContent(cursor.getString(4));
                } catch (Exception ex){
                    //
                }

            }
            while (cursor.moveToNext());
        }
        db.close();
        return allMessages;
    }



}
