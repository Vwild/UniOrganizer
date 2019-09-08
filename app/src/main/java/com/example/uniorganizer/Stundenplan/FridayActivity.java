package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;


public class FridayActivity extends ThursdayActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Friday");
        super.setWeekday("Friday");
    }
}
