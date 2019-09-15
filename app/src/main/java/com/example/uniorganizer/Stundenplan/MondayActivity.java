package com.example.uniorganizer.Stundenplan;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.uniorganizer.R;
import com.example.uniorganizer.TimetableNotificationReciever;

import java.util.ArrayList;


import java.util.Calendar;
import java.util.List;


public class MondayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static final String DATABASE_NAME = "Stundenplan";
    private static final String LECTURE_NAME = "lecture_name";
    private static final String LECTURE_ROOM = "lecture_room";
    private int REMINDER_ID = 0;
    private static final String REMINDER_CHANNEL_ID = "reminder_channel";

    public TextView textViewDay;
    public TextView textViewHintAddLecture;
    public TextView textViewHintName;
    public TextView textViewHintRoom;
    public TextView textViewHintBeginning;
    public TextView textViewHintEnd;
    private EditText inputLectureName;
    private EditText inputRoomNumber;
    private EditText inputStartTime;
    private EditText inputEndTime;


    protected Button buttonAddLecture;
    protected Button buttonBack;


    private ListView listViewDay;




    private TimetableDatabase timetableDatabase;
    private static final String TAG = MondayActivity.class.getSimpleName();
    int hourOfDay;
    int minute;
    private boolean start;
    private ArrayList<TimetableDataElement> dayList;
    private TimetableEntryItemAdapter adapterDayList;
    private int beginningHour;
    private int beginningMinute;
    private int endingHour;
    private int endingMinute;
    private String weekday = "Monday";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);
        initDatabase();
        findViews();
        setupViews();


    }

    @Override
    protected void onResume(){
        super.onResume();
        initDaylist();
    }



    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
    // Code by Julian Högerl
    private void findViews() {
        textViewDay = (TextView) findViewById(R.id.textView_day);
        textViewHintAddLecture = (TextView) findViewById(R.id.textView_hint_add_lecture);
        textViewHintName = (TextView) findViewById(R.id.textView_hint_name);
        textViewHintRoom = (TextView) findViewById(R.id.textView_hint_room);
        textViewHintBeginning = (TextView) findViewById(R.id.textView_hint_beginning_lecture);
        textViewHintEnd = (TextView) findViewById(R.id.textView_hint_end_lecture);
        inputLectureName = (EditText) findViewById(R.id.editText_lectureName);
        inputRoomNumber = (EditText) findViewById(R.id.editText_room_number);
        inputStartTime = (EditText) findViewById(R.id.editText_start_time);
        inputEndTime = (EditText) findViewById(R.id.editText_end_time);
        buttonAddLecture = (Button) findViewById(R.id.button_add_lecture);
        buttonBack = (Button) findViewById(R.id.button_back);
        listViewDay = (ListView) findViewById(R.id.listView_day);
    }

    private void initDatabase() {
        timetableDatabase = Room.databaseBuilder(getApplicationContext(),
                TimetableDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private void insertNewEntryIntoDB(String name, String room, int startH, int startMin, int endH, int endMin, String weekday) {
        TimetableDataElement timetableDataElement = new TimetableDataElement();
        timetableDataElement.setLectureName(name);
        timetableDataElement.setLectureLocation(room);
        timetableDataElement.setBeginningHour(startH);
        timetableDataElement.setBeginningMinute(startMin);
        timetableDataElement.setEndingHour(endH);
        timetableDataElement.setEndingMinute(endMin);
        timetableDataElement.setWeekDay(weekday);
        Log.d(TAG, "run: ");
        timetableDatabase.daoAccess().insertOnlyOneElement(timetableDataElement);
    }

    private void setNewNotification(String name, String room, int startH, int startMin, String weekday){
        this.REMINDER_ID = this.REMINDER_ID+1;
        Calendar calendar = Calendar.getInstance();
        //calendar.set(Calendar.DAY_OF_WEEK,7);
        switch (weekday){
            case "Monday":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                break;
            case "Tuesday":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                break;
            case "Wednesday":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                break;
            case "Thursday":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                break;
            case "Friday":
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                break;
        }
        calendar.set(Calendar.HOUR_OF_DAY, startH);
        calendar.set(Calendar.MINUTE, startMin-15);
        calendar.set(Calendar.SECOND, 0);


        Intent notifyIntent = new Intent(getApplicationContext(), TimetableNotificationReciever.class);

        notifyIntent.putExtra(LECTURE_NAME, name);
        notifyIntent.putExtra(LECTURE_ROOM,room);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), REMINDER_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        manager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis() ,AlarmManager.INTERVAL_DAY*7,pendingIntent);


    }





    private void initDaylist() {
        new Thread(new Runnable() {
        @Override
        public void run() {
            List<TimetableDataElement> entrylist = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);
            dayList.addAll(entrylist);
            adapterDayList.notifyDataSetChanged();
        }
    }).start();
    }

    private void deleteEntryFromDB(TimetableDataElement timetableDataElement){
        timetableDatabase.daoAccess().deleteOnlyOneElement(timetableDataElement);

    }
    // Code by Julian Högerl
    private void setupViews(){
        initTimeView();
        initListView();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonAddLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLectureToListView();
            }
        });
    }


    //Erstellen der beiden TimePicker Dialogen wenn die EditText-Felder geclickt wurden - Code by Julian Högerl
    private void initTimeView(){
        inputStartTime.setFocusable(false);
        inputStartTime.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v){
                createTimePickerDialogStartTime().show();
            }
        });

        inputEndTime.setFocusable(false);
        inputEndTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    createTimePickerDialogEndTime().show();
                }
        });
    }

    //Einlesen der Variablen des TimePickerDialogStartTime und Abfrage welches Zeitformat auf dem Handy des Users eingestellt is und dementsprechend handeln - Code by Julian Högerl
    private TimePickerDialog createTimePickerDialogStartTime(){

        Calendar c = Calendar.getInstance();
        hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        start = true;
        return new TimePickerDialog(this,this,hourOfDay, minute, DateFormat.is24HourFormat(this));
    }
    //Einlesen der Variablen des TimePickerDialogEndTime und Abfrage welches Zeitformat auf dem Handy des Users eingestellt is und dementsprechend handeln - Code by Julian Högerl
    private TimePickerDialog createTimePickerDialogEndTime(){

        Calendar c = Calendar.getInstance();
        hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        start = false;
        return new TimePickerDialog(this,this,hourOfDay, minute, DateFormat.is24HourFormat(this));
    }


    //Eingaben der EditText-Felder werden in Instanzvariablen gespeichert und die ausgewählte Zeit wird in den EditText-Feldern angezeigt  - Code by Julian Högerl
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if (start){
            inputStartTime.setText(hourOfDay + ":" + minute);
            this.beginningHour = hourOfDay;
            this.beginningMinute = minute;
        }else if(!start){
            inputEndTime.setText(hourOfDay + ":" + minute);
            this.endingHour = hourOfDay;
            this.endingMinute = minute;
        }
    }

    //Hinzufügen der Vorlesung, falls keines der Eingabefelder leer ist und löschen der Eingabefelder nachdem hinzufügen erfolgt ist - Code by Julian Högerl, Interaktion mit Datenbank by Vincent Eichenseher
    private void addLectureToListView(){
        String lectureName = inputLectureName.getText().toString();
        String lectureRoom = inputRoomNumber.getText().toString();
        String timeperiod = inputStartTime.getText().toString() + "-" + inputEndTime.getText().toString();

        if(!lectureName.isEmpty() && !lectureRoom.isEmpty() && !timeperiod.isEmpty()){
            new Thread(new Runnable() {
                String lectureName = inputLectureName.getText().toString();
                String lectureRoom = inputRoomNumber.getText().toString();
                @Override
                public void run() {

                    insertNewEntryIntoDB(lectureName, lectureRoom, beginningHour, beginningMinute, endingHour, endingMinute, weekday);

                }
            }).start();
            setNewNotification(lectureName,lectureRoom,beginningHour,beginningMinute,weekday);
            TimetableDataElement timetableElement = new TimetableDataElement();
            timetableElement.setLectureName(lectureName);
            timetableElement.setLectureLocation(lectureRoom);
            timetableElement.setBeginningHour(beginningHour);
            timetableElement.setBeginningMinute(beginningMinute);
            timetableElement.setEndingHour(endingHour);
            timetableElement.setEndingMinute(endingMinute);
            timetableElement.setEndingHour(endingHour);
            dayList.add(timetableElement);
            adapterDayList.notifyDataSetChanged();
            inputLectureName.setText("");
            inputRoomNumber.setText("");
            inputStartTime.setText("");
            inputEndTime.setText("");
        }
    }
    //Anzeigen der Stundenplanliste und Löschen von Vorlesungen durch onItemLongClick aus Liste und Datenbank  - Code by Julian Högerl, Interaktion mit Datenbank by Vincent Eichenseher
    private void initListView(){
        dayList = new ArrayList<>();
        adapterDayList = new TimetableEntryItemAdapter(this, dayList);
        listViewDay.setAdapter(adapterDayList);
        listViewDay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                new Thread(new Runnable() {
                    TimetableDataElement entry = dayList.get(position);
                    @Override
                    public void run() {
                       deleteEntryFromDB(entry);
                    }
                }).start();
                dayList.remove(position);
                adapterDayList.notifyDataSetChanged();
                return true;
            }
        });

    }




}
