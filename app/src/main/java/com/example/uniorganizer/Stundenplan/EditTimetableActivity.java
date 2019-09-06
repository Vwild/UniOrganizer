package com.example.uniorganizer.Stundenplan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uniorganizer.R;

public class EditTimetableActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button buttonMonday;
    protected Button buttonTuesday;
    protected Button buttonWednesday;
    protected Button buttonThursday;
    protected Button buttonFriday;
    protected TextView textView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_timetable);

        buttonMonday = findViewById(R.id.button_monday);
        buttonTuesday = findViewById(R.id.button_tuesday);
        buttonWednesday = findViewById(R.id.button_wednesday);
        buttonThursday = findViewById(R.id.button_thursday);
        buttonFriday = findViewById(R.id.button_friday);
        textView = findViewById(R.id.textView);

        buttonMonday.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.button_monday){
            buttonMondayClicked();
        }
        else if(v.getId() == R.id.button_tuesday){
            buttonTuesdayClicked();
        }
        else if(v.getId() == R.id.button_wednesday){
            buttonWednesdayClicked();
        }
        else if(v.getId() == R.id.button_thursday){
            buttonThursdayClicked();
        }
        else if(v.getId() == R.id.button_friday){
            buttonFridayClicked();
        }
    }

    private void buttonMondayClicked(){
        Intent intentMonday = new Intent(EditTimetableActivity.this, MondayActivity.class);
        startActivity(intentMonday);
    }

    private void buttonTuesdayClicked(){
        Intent intentTuesday = new Intent(EditTimetableActivity.this, TuesdayActivity.class);
        startActivity(intentTuesday);
    }
    private void buttonWednesdayClicked(){
        Intent intentWednesday = new Intent(EditTimetableActivity.this, WednesdayActivity.class);
        startActivity(intentWednesday);
    }
    private void buttonThursdayClicked(){
        Intent intentThursday = new Intent(EditTimetableActivity.this, ThursdayActivity.class);
        startActivity(intentThursday);
    }
    private void buttonFridayClicked(){
        Intent intentFriday = new Intent(EditTimetableActivity.this, FridayActivity.class);
        startActivity(intentFriday);
    }
}
