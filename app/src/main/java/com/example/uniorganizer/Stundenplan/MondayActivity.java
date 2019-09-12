package com.example.uniorganizer.Stundenplan;

import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
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

import java.util.ArrayList;


import java.util.Calendar;
import java.util.List;


public class MondayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static final String DATABASE_NAME = "Stundenplan";

    TextView textViewDay;
    TextView textViewHintAddLecture;
    TextView textViewHintName;
    TextView textViewHintRoom;
    TextView textViewHintBeginning;
    TextView textViewHintEnd;
    EditText inputLectureName;
    EditText inputRoomNumber;
    EditText inputStartTime;
    EditText inputEndTime;
    Button buttonAddLecture;
    Button buttonBack;
    Button buttonAddDay;

    ListView listViewDay;


    private TimetableEntryItemAdapter adapterDatabase;
    private ArrayList timetable;

    private TimetableDatabase timetableDatabase;
    private static final String TAG = MondayActivity.class.getSimpleName();
    int hourOfDay;
    int minute;
    private boolean start;
    private ArrayList<TimetableElement> dayList;
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
        //loadEntries();
    }

    public void loadEntries(){
        adapterDayList.open();
        adapterDayList.close();
    public void loadEntries() {

        adapterDayList.getEntries();


    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

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
        buttonAddDay = (Button) findViewById(R.id.button_add_day);
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

    private List<TimetableDataElement> findByWeekday() {
        List<TimetableDataElement> timetableDataElement = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);

        return timetableDataElement;
    }







    private void setupViews(){
        initTimeView();
        initListView();
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        buttonAddDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDayToDatabase();
            }
        });
        buttonAddLecture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLectureToListView();
            }
        });
    }



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


    private TimePickerDialog createTimePickerDialogStartTime(){

        Calendar c = Calendar.getInstance();
        hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        start = true;
        return new TimePickerDialog(this,this,hourOfDay, minute, DateFormat.is24HourFormat(this));
    }
    private TimePickerDialog createTimePickerDialogEndTime(){

        Calendar c = Calendar.getInstance();
        hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        minute = c.get(Calendar.MINUTE);
        start = false;
        return new TimePickerDialog(this,this,hourOfDay, minute, DateFormat.is24HourFormat(this));
    }



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


    private void addLectureToListView(){
        String lectureName = inputLectureName.getText().toString();
        String lectureRoom = inputRoomNumber.getText().toString();
        String timeperiod = inputStartTime.getText().toString() + "-" + inputEndTime.getText().toString();

        if(!lectureName.isEmpty() && !lectureRoom.isEmpty() && !timeperiod.isEmpty()){
            insertNewEntryIntoDB(lectureName, lectureRoom, beginningHour, beginningMinute, endingHour, endingMinute, weekday);
            TimetableElement timetableElement = new TimetableElement(lectureName, lectureRoom, beginningHour, beginningMinute, endingHour, endingMinute, weekday);
            dayList.add(timetableElement);
            adapterDayList.notifyDataSetChanged();
            inputLectureName.setText("");
            inputRoomNumber.setText("");
            inputStartTime.setText("");
            inputEndTime.setText("");
        }
    }

    private void addDayToDatabase(){

    }

    private void initListView(){
        dayList = new ArrayList<>();
        adapterDayList = new TimetableEntryItemAdapter(this, dayList);
        listViewDay.setAdapter(adapterDayList);
        listViewDay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = dayList.get(position).getLectureName();
                dayList.remove(position);
                adapterDayList.notifyDataSetChanged();
                return true;
            }
        });

    }

    private void readInput(){
        // neuer thread um daten in die datenbank einzulesen
            new Thread(new Runnable(){
                // Auslesen des Nutzer Inputs und speichern in lokale Variable
                String lecturename = inputLectureName.getText().toString();
                String lectureroom = inputRoomNumber.getText().toString();
                int beginning = Integer.parseInt(inputStartTime.getText().toString());
                int end = Integer.parseInt(inputEndTime.getText().toString());
                @Override
                public void run(){
                    //insertEntryIntoDatabase();
                }
            }).start();
        }


}
