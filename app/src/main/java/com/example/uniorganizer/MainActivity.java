package com.example.uniorganizer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uniorganizer.Stundenplan.TimetableDatabase;

public class MainActivity extends AppCompatActivity {
    private  static  final String DATABASE_NAME = "Stundenplan";
    private TimetableDatabase timetableDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimetableDatabase();
    }

    private void initTimetableDatabase(){
        timetableDatabase = Room.databaseBuilder(getApplicationContext(),TimetableDatabase.class, DATABASE_NAME).build();

    }


}
