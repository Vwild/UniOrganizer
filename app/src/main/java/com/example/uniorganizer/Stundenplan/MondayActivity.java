package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uniorganizer.R;

public class MondayActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monday);

        TextView textViewDay;
        TextView textViewHintAddLecture;
        TextView textViewHintName;
        TextView textViewHintRoom;
        TextView textViewHintBeginning;
        TextView textViewHintEnd;
        EditText editTextLectureName;
        EditText editTextRoomNumber;
        EditText editTextStartTime;
        EditText editTextEndTime;
        Button buttonAddLecture;
        Button buttonBack;
        Button buttonAddDay;
        ListView listViewDay;
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
