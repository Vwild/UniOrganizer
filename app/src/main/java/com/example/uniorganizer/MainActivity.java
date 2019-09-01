package com.example.uniorganizer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.uniorganizer.Stundenplan.DayFragment;

import com.example.uniorganizer.Stundenplan.TimetableDatabase;

public class MainActivity extends AppCompatActivity {
    private  static  final String DATABASE_NAME = "Stundenplan";
    private TimetableDatabase timetableDatabase;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimetableDatabase();

        //Einbetten von Fragment in Activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        DayFragment fragment = new DayFragment();
        // Fragments müssen in einem Layout (z.B. leeres FrameLayout)
        // platziert werden
        fragmentTransaction.add(R.id.activityMainFragmentContainer, fragment);
        // Mehrere Änderungen auf einmal möglich;
        // commit() führt Änderungen aus
        fragmentTransaction.commit();

    }

    private void initTimetableDatabase(){
        timetableDatabase = Room.databaseBuilder(getApplicationContext(),TimetableDatabase.class, DATABASE_NAME).build();

    }


}
