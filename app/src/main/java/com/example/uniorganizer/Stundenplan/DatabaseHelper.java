package com.example.uniorganizer.Stundenplan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Stundenplan";
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
    private static final String DATABASE_CREATE = "create table " + DATABASE_NAME + "("+ENTRY_ID + "integer primary key autoincrement, " + ENTRY_NAME + " text, " + ENTRY_ROOM + " text,"+ ENTRY_START_H +" text,"+ ENTRY_START_MIN + " text," + ENTRY_END_H + " text," + ENTRY_END_MIN + " text," + ENTRY_WEEKDAY+ "text );";
    private static final String DATABASE_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + DATABASE_NAME;
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;

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

    public List<TimetableElement> getEntriesByWeekday(String weekday) {


        List<TimetableElement> TimetableList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {ENTRY_ID, ENTRY_NAME, ENTRY_ROOM, ENTRY_START_H, ENTRY_START_MIN, ENTRY_END_H, ENTRY_END_MIN, ENTRY_WEEKDAY};
        Cursor c = db.rawQuery("SELECT" + columns + "FROM" + DATABASE_NAME + "WHERE" + ENTRY_WEEKDAY + "=" + weekday, null);

        if (c.moveToFirst()){

            do {
                String name = c.getString(c.getColumnIndex(ENTRY_NAME));
                String room = c.getString(c.getColumnIndex(ENTRY_ROOM));
                int startH = c.getInt(c.getColumnIndex(ENTRY_START_H));
                int startMin = c.getInt(c.getColumnIndex(ENTRY_START_MIN));
                int endH = c.getInt(c.getColumnIndex(ENTRY_END_H));
                int endMin = c.getInt(c.getColumnIndex(ENTRY_END_MIN));
                TimetableElement timetableElement = new TimetableElement(name, room, startH, startMin, endH, endMin, weekday);
                TimetableList.add(timetableElement);
                c.moveToNext();
            }while (c.moveToNext());
            c.close();
            Toast.makeText(context, "Data Loaded From SQLite Database", Toast.LENGTH_LONG).show();
        } else {
            c.close();
            Toast.makeText(context, "No Data in SQLite Database", Toast.LENGTH_LONG).show();
        }
        db.close();
        return TimetableList;

    }







}
