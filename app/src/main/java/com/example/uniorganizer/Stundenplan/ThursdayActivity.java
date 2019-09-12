package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class ThursdayActivity extends WednesdayActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textViewDay.setText("Thursday");
        super.setWeekday("Thursday");
    }
}
