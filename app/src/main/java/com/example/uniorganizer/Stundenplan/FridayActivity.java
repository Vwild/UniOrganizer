package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;

//Alle anderen WochentagActivities erben von der MondayActivity, nur textViewDay wird dementsprechend angepasst - Code by Julian Högerl
public class FridayActivity extends ThursdayActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Friday");
        super.setWeekday("Friday");
    }
}
