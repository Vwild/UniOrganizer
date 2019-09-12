package com.example.uniorganizer.Stundenplan;

import androidx.appcompat.app.AppCompatActivity;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;



import com.example.uniorganizer.R;

import java.util.ArrayList;
import java.util.Calendar;

//newCommit
public class MondayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

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
    ImageButton itemDeleteButton;
    ListView listViewDay;

    private TimetableEntryItemAdapter adapterDatabase;
    private ArrayList timetable;
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
        //loadEntries();
        super.onResume();
    }

    public void loadEntries(){
        adapterDayList.open();
        adapterDayList.getEntriesByWeekday(weekday);
        adapterDayList.close();
    }

    public void setWeekday(String weekday){
        this.weekday = weekday;
    }

    private void findViews(){
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
        adapterDatabase = new TimetableEntryItemAdapter(this, timetable);
        adapterDatabase.open();
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
                addDayToDatabase();
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


    private void addDayToDatabase(){
        String lectureName = inputLectureName.getText().toString();
        String lectureRoom = inputRoomNumber.getText().toString();
        String timeperiod = inputStartTime.getText().toString() + "-" + inputEndTime.getText().toString();

        if(!lectureName.isEmpty() && !lectureRoom.isEmpty() && !timeperiod.isEmpty()){
            adapterDayList.open();
            adapterDayList.insertIntoDatabase(lectureName, lectureRoom, beginningHour, beginningMinute, endingHour, endingMinute, weekday);
            adapterDayList.close();
            TimetableElement timetableElement = new TimetableElement(lectureName, lectureRoom, beginningHour, beginningMinute, endingHour, endingMinute, weekday);
            dayList.add(timetableElement);
            adapterDayList.notifyDataSetChanged();
            inputLectureName.setText("");
            inputRoomNumber.setText("");
            inputStartTime.setText("");
            inputEndTime.setText("");
        }
    }

    private void initListView(){
        dayList = new ArrayList<>();
        adapterDayList = new TimetableEntryItemAdapter(this, dayList);
        listViewDay.setAdapter(adapterDayList);
        listViewDay.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                String name = dayList.get(position).getLectureName();

                adapterDayList.open();
                adapterDayList.deleteFromDatabase(name);
                adapterDayList.close();

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
