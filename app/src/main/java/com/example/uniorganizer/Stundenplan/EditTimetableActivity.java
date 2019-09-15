package com.example.uniorganizer.Stundenplan;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.uniorganizer.R;

// Code by Julian Högerl
public class EditTimetableActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button buttonMonday;
    protected Button buttonTuesday;
    protected Button buttonWednesday;
    protected Button buttonThursday;
    protected Button buttonFriday;
    protected Button buttonBack;
    protected TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_timetable);
        initButtons();
    }

    private void initButtons (){
        buttonMonday = (Button) findViewById(R.id.button_monday);
        buttonTuesday = (Button) findViewById(R.id.button_tuesday);
        buttonWednesday = (Button) findViewById(R.id.button_wednesday);
        buttonThursday = (Button) findViewById(R.id.button_thursday);
        buttonFriday = (Button )findViewById(R.id.button_friday);
        buttonBack = (Button) findViewById(R.id.button_back);
        textView = (TextView) findViewById(R.id.textView);

        buttonMonday.setOnClickListener(this);
        buttonTuesday.setOnClickListener(this);
        buttonWednesday.setOnClickListener(this);
        buttonThursday.setOnClickListener(this);
        buttonFriday.setOnClickListener(this);
        buttonBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.button_monday){
            startDayActivity(MondayActivity.class);
        }
        else if(v.getId() == R.id.button_tuesday){
            startDayActivity(TuesdayActivity.class);
        }
        else if(v.getId() == R.id.button_wednesday){
            startDayActivity(WednesdayActivity.class);
        }
        else if(v.getId() == R.id.button_thursday){
            startDayActivity(ThursdayActivity.class);
        }
        else if(v.getId() == R.id.button_friday){
            startDayActivity(FridayActivity.class);
        }
        else if(v.getId() == R.id.button_back){
            finish();
        }
    }

    private void startDayActivity (Class dayClass){
        Intent intent = new Intent(EditTimetableActivity.this,dayClass);
        startActivity(intent);
    }

}
