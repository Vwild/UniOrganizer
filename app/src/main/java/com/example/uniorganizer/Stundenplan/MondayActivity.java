package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.uniorganizer.R;

public class MondayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);
    }

    private void instertEntryintoDatabase(String lecturename, String roomname ){
        TimetableElement newEntry = new TimetableElement();
        newEntry.setLectureName(lecturename);
        newEntry.setLectureLocation(roomname);
    }
}
