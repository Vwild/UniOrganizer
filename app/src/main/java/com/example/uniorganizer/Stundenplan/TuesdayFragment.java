package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.uniorganizer.R;

import java.util.ArrayList;
import java.util.List;

public class TuesdayFragment extends Fragment {
    public static final String DATABASE_NAME = "Stundenplan";


    private TextView textView;
    private ListView listView;
    private ArrayList<TimetableDataElement> tuesdayList;
    private TimetableEntryItemAdapter tuesdayListAdapter;
    private TimetableDatabase timetableDatabase;
    private String weekday = "Tuesday";

    protected Button buttonSwitchFragmentBackward;
    protected Button buttonSwitchFragmentForward;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View von fragment_timetable_day erzeugen
        View layout = inflater.inflate(R.layout.fragment_timetable_day, null);
        textView =   layout.findViewById(R.id.dayname_textView1);
        listView =   layout.findViewById(R.id.list_view_timetable_day);
        buttonSwitchFragmentBackward = (Button) layout.findViewById(R.id.button_switch_fragment_backward);
        buttonSwitchFragmentForward = (Button) layout.findViewById(R.id.button_switch_fragment_forward);
        initDatabase();
        setOnClickListener();
        tuesdayList = new ArrayList<>();
        tuesdayListAdapter = new TimetableEntryItemAdapter(getContext(), tuesdayList);
        listView.setAdapter(tuesdayListAdapter);

        textView.setText("Tuesday");


        return layout;
    }

    private void setOnClickListener(){
        buttonSwitchFragmentBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DayFragment monday = new DayFragment();
                getFragmentManager().beginTransaction().replace(R.id.activityMainFragmentContainer, monday).addToBackStack(null).commit();

            }
        });
        buttonSwitchFragmentForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.activityMainFragmentContainer, new WednesdayFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        tuesdayList.clear();
        initTuesdayList();
        tuesdayListAdapter.notifyDataSetChanged();

    }



    private void initDatabase() {
        timetableDatabase = Room.databaseBuilder(getContext(),
                TimetableDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initTuesdayList() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<TimetableDataElement> entrylist = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);
                tuesdayList.addAll(entrylist);



            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
