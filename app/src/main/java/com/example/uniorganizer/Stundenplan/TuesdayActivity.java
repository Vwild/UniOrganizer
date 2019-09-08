package com.example.uniorganizer.Stundenplan;


import android.os.Bundle;

public class TuesdayActivity extends MondayActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Tuesday");
        super.setWeekday("Tuesday");
    }
}
