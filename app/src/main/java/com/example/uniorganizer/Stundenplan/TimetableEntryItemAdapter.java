package com.example.uniorganizer.Stundenplan;

import androidx.room.Room;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniorganizer.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;



/*
Dem Adapter muss eine ArrayList mit TimetableElements aus der Datenbank übergeben werden.
Diese werden dann in einem Timetable_entry_item Objekt (in einer ListView) dargestellt.
*/

public class TimetableEntryItemAdapter extends ArrayAdapter<TimetableElement> {

    private List<TimetableElement> timetableEntries;
    private Context context;
    private static final String DATABASE_NAME = "Stundenplan";
    private static final String KEY_WEEKDAY = "week_day";
    private static final String KEY_NAME = "lecture_name";
    private static final int DATABASE_VERSION = 1;
    private static final String ENTRY_ID = "_id";
    private static final String ENTRY_NAME = "lecture_name";
    private static final String ENTRY_ROOM = "lecture_room";
    private static final String ENTRY_START_H = "beginning_hour";
    private static final String ENTRY_START_MIN = "beginning_minute";
    private static final String ENTRY_END_H = "ending_hour";
    private static final String ENTRY_END_MIN = "ending_minute";
    private static final String ENTRY_WEEKDAY = "week_day";
    private List<TimetableElement> TimetableList;


    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;


    public TimetableEntryItemAdapter(Context context, List<TimetableElement> timetableEntries ) {
        super(context, R.layout.timetable_entry_item, timetableEntries);
        this.context = context;
        this.timetableEntries = timetableEntries;
        helper = new DatabaseHelper(context);



    }

    //methoden zum öffnen und schließen der datenbank
    public TimetableEntryItemAdapter open() throws SQLiteException {
        db = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public void insertIntoDatabase(String lecturename, String roomname, int starthour, int startminutes, int endhour, int endminutes, String weekday) {

        helper = new DatabaseHelper(context);
        ((DatabaseHelper) helper).insertIntoDatabase(lecturename,roomname,starthour,startminutes,endhour,endminutes,weekday);
    }

    public void deleteFromDatabase(String name) {

        helper = new DatabaseHelper(context);
        ((DatabaseHelper) helper).deleteFromDatabase(name);
    }

    public List<TimetableElement> getEntries() {


        List<TimetableElement> TimetableList = new ArrayList<>();


        String[] columns = {ENTRY_ID, ENTRY_NAME, ENTRY_ROOM, ENTRY_START_H, ENTRY_START_MIN, ENTRY_END_H, ENTRY_END_MIN, ENTRY_WEEKDAY};
        Cursor c = db.rawQuery("SELECT" + columns + "FROM" + DATABASE_NAME , null);

        if (c.moveToFirst()){

            do {
                String name = c.getString(c.getColumnIndex(ENTRY_NAME));
                String room = c.getString(c.getColumnIndex(ENTRY_ROOM));
                int startH = c.getInt(c.getColumnIndex(ENTRY_START_H));
                int startMin = c.getInt(c.getColumnIndex(ENTRY_START_MIN));
                int endH = c.getInt(c.getColumnIndex(ENTRY_END_H));
                int endMin = c.getInt(c.getColumnIndex(ENTRY_END_MIN));
                String weekday = c.getString(c.getColumnIndex(ENTRY_WEEKDAY));
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


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.timetable_entry_item, null);
        }

        //Button buttonDelete = v.findViewById(R.id.entry_item_deleteButton);
        TextView title = v.findViewById(R.id.textView_entry_item_title);
        TextView timeperiod = v.findViewById(R.id.textView_entry_item_timeperiod);
        TextView description = v.findViewById(R.id.textView_entry_item_description);
        TimetableElement entry = timetableEntries.get(position);

        title.setText(entry.getLectureName());
        timeperiod.setText(entry.getBeginningHour() + ":" + entry.getBeginningMinute() + " - " + entry.getEndingHour() + ":" + entry.getEndingMinute());
        description.setText(entry.getLectureLocation());

        return v;
    }

}