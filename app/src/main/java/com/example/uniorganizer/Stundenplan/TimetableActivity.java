package com.example.uniorganizer.Stundenplan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.uniorganizer.R;

public class TimetableActivity extends AppCompatActivity {

    private DayFragment dayFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
    }
}
