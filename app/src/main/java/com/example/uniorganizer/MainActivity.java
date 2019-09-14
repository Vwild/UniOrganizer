package com.example.uniorganizer;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.uniorganizer.Stundenplan.DayFragment;
import android.content.Intent;

import com.example.uniorganizer.Stundenplan.EditTimetableActivity;
import com.example.uniorganizer.Friendtransaction.FriendsActivity;
import com.example.uniorganizer.Stundenplan.TimetableDataElement;
import com.example.uniorganizer.Stundenplan.TimetableDatabase;
import com.example.uniorganizer.Stundenplan.TimetableEntryItemAdapter;
import com.example.uniorganizer.Stundenplan.TuesdayFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  static  final String DATABASE_NAME = "Stundenplan";
    private static final String REMINDER_CHANNEL_ID = "reminder_channel";
    private static final String CHANNEL_NAME = "My_reminder_channel";
    private static final String CHANNEL_DESCRIPTION = "channel_for_reminder_notifications";




    protected Button buttonTimetable;
    protected Button buttonFriends;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel(CHANNEL_NAME,CHANNEL_DESCRIPTION);


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

    public void initNotifications(){
        //coming soon





    }

    private void createNotificationChannel(String channel_name, String channel_description) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = channel_name;
            String description = channel_description;
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(REMINDER_CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }






}
