package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uniorganizer.R;



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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

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
        // Auslesen des Nutzer Inputs und speichern in lokale Variable

    }



    private void insertEntryIntoDatabase(String lecturename, String roomname, int starttime, int endtime ){
        TimetableElement newEntry = new TimetableElement();
        newEntry.setLectureName(lecturename);
        newEntry.setLectureLocation(roomname);
        newEntry.setBeginn(starttime);
        newEntry.setEnding(endtime);
        //TimetableDatabase.daoAccess().insertOnlyOneElement(newEntry);
    }
}
