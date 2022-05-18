package com.example.bluetooth_messaging_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "Userdata.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // tables of database constructed here
        db.execSQL("create Table UserDetails (UserID INTEGER primary key, username TEXT,user_profile_pic BLOB)");
        db.execSQL("create Table DirectContacts (ContactID INTEGER primary key, username TEXT,contact_profile_pic BLOB)");
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
}
