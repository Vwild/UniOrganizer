package com.example.uniorganizer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uniorganizer.Stundenplan.EditTimetableActivity;
import com.example.uniorganizer.Friendtransaction.FriendsActivity;
import com.example.uniorganizer.Stundenplan.TimetableDataElement;
import com.example.uniorganizer.Stundenplan.TimetableDatabase;
import com.example.uniorganizer.Stundenplan.TimetableEntryItemAdapter;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private  static  final String DATABASE_NAME = "Stundenplan";
    private TimetableDatabase timetableDatabase;
    private static final String REMINDER_CHANNEL_ID = "reminder_channel";
    private static final String CHANNEL_NAME = "My_reminder_channel";
    private static final String CHANNEL_DESCRIPTION = "channel_for_reminder_notifications";


    private TimetableEntryItemAdapter adapter;
    private ArrayList<TimetableDataElement> timetable;

    private ListView timetableListView;
    private TextView dayTextView;

    protected Button buttonTimetable;
    protected Button buttonFriends;
    protected Button buttonSwitchDayBackward;
    protected Button buttonSwitchDayForward;
    private String weekday;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDatabase();
        createNotificationChannel(CHANNEL_NAME,CHANNEL_DESCRIPTION);
        getCurrentDay();


        //Setzen von Referenzen der Objektvariablen auf die definierten Views des Layouts der Acitivity
        buttonTimetable = (Button) findViewById(R.id.button_timetable);
        buttonFriends = (Button) findViewById(R.id.button_friends);
        buttonSwitchDayBackward = (Button) findViewById(R.id.button_switch_day_backward);
        buttonSwitchDayForward = (Button) findViewById(R.id.button_switch_day_forward);
        timetableListView = (ListView) findViewById(R.id.dayList_listView);
        dayTextView = (TextView) findViewById(R.id.textView_day);
        dayTextView.setText(weekday);
        timetable = new ArrayList<>();
        adapter = new TimetableEntryItemAdapter(this, timetable);
        timetableListView.setAdapter(adapter);

        buttonTimetable.setOnClickListener(this);
        buttonFriends.setOnClickListener(this);
        buttonSwitchDayBackward.setOnClickListener(this);
        buttonSwitchDayForward.setOnClickListener(this);




    }

    @Override
    protected void onResume() {
        super.onResume();
        timetable.clear();
        initTimetableList();
        adapter.notifyDataSetChanged();
    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "listContent", );
    }*/

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_timetable){
            buttonTimetableClicked();
        }
        else if(v.getId() == R.id.button_friends){
            buttonFriendsClicked();
        }else if(v.getId() == R.id.button_switch_day_backward){
            buttonSwitchDayBackwardClicked();
        }else if(v.getId() == R.id.button_switch_day_forward){
            buttonSwitchDayForwardClicked();
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
    private void buttonSwitchDayBackwardClicked(){
        switch (weekday){
            case "Monday":
                this.weekday = "Friday";
                break;
            case "Tuesday":
                this.weekday = "Monday";
                break;
            case "Wednesday":
                this.weekday = "Tuesday";
                break;
            case "Thursday":
                this.weekday = "Wednesday";
                break;
            case "Friday":
                this.weekday = "Thursday";
                break;
        }
        dayTextView.setText(weekday);
        timetable.clear();
        initTimetableList();
        adapter.notifyDataSetChanged();
    }
    private void buttonSwitchDayForwardClicked(){
        switch (weekday){
            case "Monday":
                this.weekday = "Tuesday";
                break;
            case "Tuesday":
                this.weekday = "Wednesday";
                break;
            case "Wednesday":
                this.weekday = "Thursday";
                break;
            case "Thursday":
                this.weekday = "Friday";
                break;
            case "Friday":
                this.weekday = "Monday";
                break;
        }
        dayTextView.setText(weekday);
        timetable.clear();
        initTimetableList();
        adapter.notifyDataSetChanged();
    }

    private void initDatabase() {
        timetableDatabase = Room.databaseBuilder(this.getApplicationContext(),
                TimetableDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
    private void initTimetableList() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<TimetableDataElement> entrylist = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);
                timetable.addAll(entrylist);


            }

        }).start();
    }

    private void createNotificationChannel (String channel_name, String
            channel_description){

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

    private void getCurrentDay(){
        Calendar calendar = Calendar.getInstance();
        int day= calendar.get(Calendar.DAY_OF_WEEK);
        switch (day){
            // Weekday=Monday bei Samstag und Sonntag, da man sich am Wochenende nur auf dem Kommenden montag einstellt und es dafür keine eigenen Stundenplaneinträge gibt
            case Calendar.SUNDAY:
                this.weekday = "Monday";
                break;
            case Calendar.MONDAY:
                this.weekday = "Monday";
                break;
            case Calendar.TUESDAY:
                this.weekday = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                this.weekday = "Wednesday";
                break;
            case Calendar.THURSDAY:
                this.weekday = "Thursday";
                break;
            case Calendar.FRIDAY:
                this.weekday = "Friday";
                break;
            case Calendar.SATURDAY:
                this.weekday = "Monday";
        }


    }








}
