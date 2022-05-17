package com.example.bluetooth_messaging_app;

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
        db.execSQL("create Table UserDetails (userID INTEGER primary key autoincrement, username TEXT,profile_pic BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop tables to upgrade database then create again
        db.execSQL("drop Table if exists UserDetails");
        onCreate(db);
    }


    // insert ,delete ,update and view methods
}
