package com.example.uniorganizer;

import android.arch.persistence.room.Room;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.uniorganizer.Stundenplan.DayFragment;
import android.content.Intent;

import com.example.uniorganizer.Stundenplan.EditTimetableActivity;
import com.example.uniorganizer.Stundenplan.FriendsActivity;
import com.example.uniorganizer.Stundenplan.TimetableDatabase;
import com.example.uniorganizer.Stundenplan.TimetableElement;
import com.example.uniorganizer.Stundenplan.TimetableEntryItemAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  static  final String DATABASE_NAME = "Stundenplan";
    private TimetableDatabase timetableDatabase;

    private TimetableEntryItemAdapter adapter;
    private ArrayList timetable;

    protected Button buttonTimetable;
    protected Button buttonFriends;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();

        //Setzen von Referenzen der Objektvariablen auf die definierten Views des Layouts der Acitivity
        buttonTimetable = findViewById(R.id.button_timetable);
        buttonFriends = findViewById(R.id.button_friends);


        buttonTimetable.setOnClickListener(this);
        buttonFriends.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_timetable){
            buttonTimetableClicked();
        }
        else if(v.getId() == R.id.button_friends){
            buttonFriendsClicked();
        }
    }
    private void buttonTimetableClicked(){
        Intent intentTimetable = new Intent(MainActivity.this, EditTimetableActivity.class);
        startActivity(intentTimetable);
    }
    private void buttonFriendsClicked(){
        Intent intentFriends = new Intent(MainActivity.this, FriendsActivity.class);
        startActivity(intentFriends);
    }

    private void initDatabase() {
        adapter = new TimetableEntryItemAdapter(this, timetable);
        adapter.open();
    }

}
