package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;

public class ThursdayActivity extends WednesdayActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Thursday");
        super.setWeekday("Thursday");
    }
}
