package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class WednesdayActivity extends TuesdayActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Wednesday");
    }
}
