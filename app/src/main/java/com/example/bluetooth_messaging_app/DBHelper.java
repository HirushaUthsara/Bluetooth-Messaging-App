package com.example.bluetooth_messaging_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.bluetooth_messaging_app.Models.Users;

import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;


public class DBHelper extends SQLiteOpenHelper {


    private final ArrayList<Users> users = new ArrayList<>();    // arraylist to hold contacts
    private final ArrayList<Message> allMessages = new ArrayList<>();    // arraylist to hold messages of one certain contact
    private Context context;

    // constructor
    public DBHelper(Context context) {
        super(context, "Userdata.db", null , 1);
    }

    // create tables needed inside the Userdata.db database
    @Override
    public void onCreate(SQLiteDatabase db) {

        // tables of database constructed here
        // userId and contactID will be basically their Mac Address
        // store app user details
        db.execSQL("create Table UserDetails (UserID TEXT primary key, username TEXT,user_profile_pic INTEGER)");
        // store contact details
        db.execSQL("create Table DirectContacts (ContactID TEXT primary key, username TEXT,contact_profile_pic INTEGER)");
        // store messages
        db.execSQL("create Table Messages (MESSAGEID INTEGER primary key AUTOINCREMENT, TIME INTEGER,SENDERID TEXT, RECEIVERID TEXT, CONTENT TEXT)");
    }

    // create new versions of database if needed with new schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop tables to upgrade database then create again
        db.execSQL("drop Table if exists UserDetails");
        db.execSQL("drop Table if exists DirectContacts");
        db.execSQL("drop Table if exists Messages");
        onCreate(db);
    }


    // database operations

    // store the message objects in Messages table
    public void storeMessage(Message msg){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("TIME",msg.getTime());
        contentValues.put("SENDERID",msg.getSenderId());
        contentValues.put("RECEIVERID",msg.getReceiverId());
        contentValues.put("CONTENT",msg.getContent());

        // save to table
        sqLiteDatabase.insert("Messages",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

    // get the username from the UserDetails table and return username of App user as a string
    public String getUserName(){
        String username = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";      // select query

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){          // if no entry return null
            username = cursor.getString(1);
        }
        db.close();
        return username;
    }

    // return the user profile picture as an integer
    public int getUserProfile(){
        int profile_pic = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            profile_pic = cursor.getInt(2);  // get the value from column no 2
        }
        db.close();
        return profile_pic;
    }

    // update the user profile picture
    public void setUserProfile(String userid ,int pic){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_profile_pic",pic);

        Cursor cursor = db.rawQuery("SELECT * FROM UserDetails Where UserID = ? ",new String[] {userid});

        long result = db.update("UserDetails",contentValues,"UserID = ?",new String[]{userid});

        db.close();

    }

    // return the userid of app user
    public String getUserID(){
        String userid = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM UserDetails LIMIT 1";   // query to select the first raw of UserDetails

        Cursor cursor = db.rawQuery(query,null);

        if (cursor.moveToFirst()){
            userid = cursor.getString(0);
        }
        // close the database
        db.close();
        return userid;
    }


    public void storeContact(Contact cnt){     // store the Contact object in DirectContacts Table

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("ContactID",cnt.getId());
        contentValues.put("username",cnt.getUsername());
        contentValues.put("contact_profile_pic",cnt.getProfile_pic());

        // save to table
        sqLiteDatabase.insert("DirectContacts",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }


    public void initializeUser(String username,int pic){     // initialize user details

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("UserID","MyUserId");   // default userId
        contentValues.put("username",username);


        contentValues.put("user_profile_pic", pic);

        // save to table
        sqLiteDatabase.insert("UserDetails",null,contentValues);
        // close database
        sqLiteDatabase.close();
    }

//    // return the mac address of the device nic as a string
//    public static String getMacAddress() {
//
//        try {
//            ArrayList<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
//            for (NetworkInterface nif : all) {
//                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;
//
//                byte[] macBytes = nif.getHardwareAddress();
//                if (macBytes == null) {
//                    return "";
//                }
//
//                StringBuilder res1 = new StringBuilder();
//                for (byte b : macBytes) {
//                    String hex = Integer.toHexString(b & 0xFF);
//                    if (hex.length() == 1)
//                        hex = "0".concat(hex);
//                    res1.append(hex.concat(":"));
//                }
//
//                if (res1.length() > 0) {
//                    res1.deleteCharAt(res1.length() - 1);
//                }
//                return res1.toString();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return "";
//    }

//    public ArrayList<Contact> loadAllContacts(){
//
//        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM  DirectContacts";
//
//        Cursor cursor = db.rawQuery(query,null);
//
//        if (cursor.moveToFirst()){
//            do {
//                Contact c = new Contact();
//                c.setId(cursor.getString(0));
//                c.setUsername(cursor.getString(1));
//                c.setProfile_pic(cursor.getInt(2));
//
//                allContacts.add(c);
//            }
//            while (cursor.moveToNext());
//        }
//        db.close();
//        return allContacts;
//    }

    public ArrayList<Message> loadMessages(String contactId){

        System.out.println("Contact id --------------------------------------------->>>>>>>>>>>>>>>>>>>>"+contactId);

        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT * FROM  Messages Where SENDERID = "+contactId+" OR RECEIVERID = "+contactId+" ORDER BY TIME";
        String query = "SELECT * FROM  Messages";

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

                    if (msg.getSenderId().equals(contactId) || msg.getReceiverId().equals(contactId)){
                        allMessages.add(msg);
                    }

                    //////////////////////////  test
                    System.out.println("_________________________________________________________________________________________");
                    for (int i = 0; i < allMessages.size(); i++) {
                        System.out.println(allMessages.get(i)+"\n");
                    }
                    System.out.println("_________________________________________________________________________________________");
                    ///////////

                } catch (Exception ex){
                    ex.printStackTrace();
                }

            }
            while (cursor.moveToNext());
        } else {
            System.out.println("-------------------------------------------------------------------------------------->>>>>>>>>>");
            System.out.println("------------------------------------------No Messages-------------------------------------------");
            System.out.println("-------------------------------------------------------------------------------------->>>>>>>>>>");
        }
        db.close();
        return allMessages;
    }

    // return Users as an ArrayList

    public ArrayList<Users> loadAllUsers(){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM  DirectContacts";        // select all contacts from DirectContact table

        Cursor cursor = db.rawQuery(query,null);    // create cursor object

        if (cursor.moveToFirst()){   // check for empty cursor
            do {
                try {

                    // extract data from DirectContact Table create User Objects
                    int profilepicture = cursor.getInt(2);
                    String username = cursor.getString(1);
                    String userID = cursor.getString(0);

                    String lastmessage = "Hello!";             // get the last message sent by certain contact

                    Users u = new Users(userID,profilepicture,username,lastmessage);  // create an User object
                    // add user object to arraylist
                    users.add(u);
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            while (cursor.moveToNext());    // next item on cursor
        }
        db.close();       // close the database

        return users;
    }



}
