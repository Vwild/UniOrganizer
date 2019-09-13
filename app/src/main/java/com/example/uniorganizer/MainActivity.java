package com.example.uniorganizer;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.uniorganizer.Stundenplan.DayFragment;
import android.content.Intent;

import com.example.uniorganizer.Stundenplan.EditTimetableActivity;
import com.example.uniorganizer.Friendtransaction.FriendsActivity;
import com.example.uniorganizer.Stundenplan.TimetableDataElement;
import com.example.uniorganizer.Stundenplan.TimetableEntryItemAdapter;
import com.example.uniorganizer.Stundenplan.TuesdayFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  static  final String DATABASE_NAME = "Stundenplan";
    //private TimetableDatabase timetableDatabase;

    private TimetableEntryItemAdapter adapter;
    private ArrayList<TimetableDataElement> timetable;

    protected Button buttonTimetable;
    protected Button buttonFriends;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initDatabase();

        //Setzen von Referenzen der Objektvariablen auf die definierten Views des Layouts der Acitivity
        buttonTimetable = (Button) findViewById(R.id.button_timetable);
        buttonFriends = (Button) findViewById(R.id.button_friends);

        buttonTimetable.setOnClickListener(this);
        buttonFriends.setOnClickListener(this);



        //Einbetten von Fragment in Activity
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        DayFragment mondayFragment = new DayFragment();
        //TuesdayFragment tuesdayFragment = new TuesdayFragment();


        // Fragments müssen in einem Layout (z.B. leeres FrameLayout)
        // platziert werden
        fragmentTransaction.add(R.id.activityMainFragmentContainer, mondayFragment);
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

    /*private void initDatabase() {
        adapter = new TimetableEntryItemAdapter(this, timetable);
        adapter.open();
    }*/

}
