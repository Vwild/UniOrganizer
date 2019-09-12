package com.example.uniorganizer.Stundenplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Stundenplan";
    private static final String ENTRY_WEEKDAY = "week_day";
    private static final String KEY_NAME = "lecture_name";
    private static final int DATABASE_VERSION = 1;
    private static final String ENTRY_NAME = "lecture_name";
    private static final String ENTRY_ROOM = "lecture_room";
    private static final String ENTRY_START_H = "beginning_hour";
    private static final String ENTRY_START_MIN = "beginning_minute";
    private static final String ENTRY_END_H = "ending_hour";
    private static final String ENTRY_END_MIN = "ending_minute";
    private static final String ENTRY_ID = "_id";
    private static final String DATABASE_CREATE = "create table " + DATABASE_NAME + "("+ENTRY_ID + "integer primary key autoincrement, " + ENTRY_NAME + " TEXT, " + ENTRY_ROOM + " text not null,"+ ENTRY_START_H +" text not null,"+ ENTRY_START_MIN + " text not null," + ENTRY_END_H + " text not null," + ENTRY_END_MIN + " text not null," + ENTRY_WEEKDAY+ " );";
    private static final String DATABASE_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DATABASE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //vorerst leer
        db.execSQL(DATABASE_DELETE_ENTRIES);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }







}
