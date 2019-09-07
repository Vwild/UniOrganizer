package com.example.uniorganizer.Stundenplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Stundenplan";
    private static final String KEY_WEEKDAY = "week_day";
    private static final String KEY_NAME = "lecture_name";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE = "create table " + DATABASE_NAME + " (_id integer primary key autoincrement, " + KEY_WEEKDAY + " text not null, " + KEY_NAME + " text not null);";
    private static final String ENTRY_NAME = "lecture_name";
    private static final String ENTRY_ROOM = "lecture_room";
    private static final String ENTRY_START_H = "beginning_hour";
    private static final String ENTRY_START_MIN = "beginning_minute";
    private static final String ENTRY_END_H = "ending_hour";
    private static final String ENTRY_END_MIN = "ending_minute";


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

    }




    public void insertIntoDatabase(String lecturename, String roomname, int starthour, int startminutes, int endhour, int endminutes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ENTRY_NAME, lecturename);
        cv.put(ENTRY_ROOM, roomname);
        cv.put(ENTRY_START_H, starthour);
        cv.put(ENTRY_START_MIN, startminutes);
        cv.put(ENTRY_END_H, endhour);
        cv.put(ENTRY_END_MIN, endminutes);

        db.insert(DATABASE_NAME, null, cv);
        db.close();
    }
}
