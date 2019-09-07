package com.example.uniorganizer.Stundenplan;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uniorganizer.R;

import java.util.ArrayList;

import static com.example.uniorganizer.Stundenplan.TimetableDatabase.MIGRATION_1_2;


public class MondayActivity extends AppCompatActivity {

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
    private TimetableDatabase timetableDatabase;
    private TimetableEntryItemAdapter adapter;
    private ArrayList timetable;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        initDatabase();

        findViews();
        setupViews();

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
        adapter = new TimetableEntryItemAdapter(this, timetable);
        adapter.open();
    }

    private void setupViews(){
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
                readInput();
            }
        });
    }

    private void addDayToDatabase(){

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

    private void insertEntryIntoDatabase(String lecturename, String roomname, int starthour,int startminute, int endhour,int endminute ){
        adapter.insertIntoDatabase(lecturename,roomname,starthour,startminute,endhour,endminute);
    }
}
