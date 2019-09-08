package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.room.Room;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniorganizer.R;

import java.util.ArrayList;
import java.util.List;



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
    private static final String ENTRY_NAME = "lecture_name";
    private static final String ENTRY_ROOM = "lecture_room";
    private static final String ENTRY_START_H = "beginning_hour";
    private static final String ENTRY_START_MIN = "beginning_minute";
    private static final String ENTRY_END_H = "ending_hour";
    private static final String ENTRY_END_MIN = "ending_minute";
    private static final String ENTRY_WEEKDAY = "week_day";


    private SQLiteOpenHelper helper;
    private SQLiteDatabase db;


    public TimetableEntryItemAdapter(Context context, List<TimetableElement> timetableEntries) {
        super(context, R.layout.timetable_entry_item, timetableEntries);
        this.context = context;
        this.timetableEntries = timetableEntries;
        helper = new DatabaseHelper(context);


    }

    //methoden zumöffnen und schließen der datenbank
    public TimetableEntryItemAdapter open() throws SQLiteException {
        db = helper.getWritableDatabase();
        return this;
    }

    public void close() {
        helper.close();
    }

    public void insertIntoDatabase(String lecturename, String roomname, int starthour, int startminutes, int endhour, int endminutes, String weekday) {
        ContentValues cv = new ContentValues();
        cv.put(ENTRY_NAME, lecturename);
        cv.put(ENTRY_ROOM, roomname);
        cv.put(ENTRY_START_H, starthour);
        cv.put(ENTRY_START_MIN, startminutes);
        cv.put(ENTRY_END_H, endhour);
        cv.put(ENTRY_END_MIN, endminutes);
        cv.put(ENTRY_WEEKDAY, weekday);
        db.insert(DATABASE_NAME, null, cv);
        Toast.makeText(context, "Data Inserted To Sqlite Database", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void deleteFromDatabase(String name) {
        db.delete(DATABASE_NAME, ENTRY_NAME + "=?", new String[]{name});
        Toast.makeText(context, "Data Deleted From Sqlite Database", Toast.LENGTH_LONG).show();
        db.close();
    }

    public void getEntriesByWeekday(String weekday) {

        db.execSQL("SELECT" +"FROM"+ DATABASE_NAME +"WHERE"+ KEY_WEEKDAY +"="+ weekday);

    }













    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
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
        timeperiod.setText(entry.getBeginningHour()+":" + entry.getBeginningMinute() + " - " + entry.getEndingHour()+ ":" + entry.getEndingMinute());
        description.setText(entry.getLectureLocation());

        return v;
    }


}
