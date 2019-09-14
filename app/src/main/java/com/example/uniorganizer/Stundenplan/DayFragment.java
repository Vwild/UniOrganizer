package com.example.uniorganizer.Stundenplan;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.example.uniorganizer.R;

import java.util.ArrayList;
import java.util.List;

public class DayFragment extends Fragment {

    public static final String DATABASE_NAME = "Stundenplan";


    private TextView textView;
    private ListView listView;
    private ArrayList<TimetableDataElement> mondayList;
    private TimetableEntryItemAdapter mondayListAdapter;
    private TimetableDatabase timetableDatabase;
    private String weekday = "Monday";


    protected Button buttonSwitchFragmentBackward;
    protected Button buttonSwitchFragmentForward;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View von fragment_timetable_day erzeugen
        View layout = inflater.inflate(R.layout.fragment_timetable_day, null);
        textView =   layout.findViewById(R.id.dayname_textView1);
        listView =   layout.findViewById(R.id.list_view_timetable_day);
        buttonSwitchFragmentBackward = layout.findViewById(R.id.button_switch_fragment_backward);
        buttonSwitchFragmentForward = layout.findViewById(R.id.button_switch_fragment_forward);
        initDatabase();
        //setOnClickListener();
        mondayList = new ArrayList<>();
        mondayListAdapter = new TimetableEntryItemAdapter(getContext(), mondayList);
        listView.setAdapter(mondayListAdapter);

        textView.setText("Monday");




        return layout;
    }
    /*
    private void setOnClickListener(){
        buttonSwitchFragmentBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FridayFragment friday = new FridayFragment();
                if(friday.isAdded()){
                    getFragmentManager().beginTransaction().show(friday).commit();
                }else {
                    friday.onPause();
                    getFragmentManager().beginTransaction().add(R.id.activityMainFragmentContainer, friday).commit();
                }


            }
        });
        buttonSwitchFragmentForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TuesdayFragment tuesday = new TuesdayFragment();
                getFragmentManager().beginTransaction().replace(R.id.activityMainFragmentContainer, tuesday).addToBackStack(null).commit();
            }
        });
    }*/

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onResume(){
        super.onResume();
        mondayList.clear();
        initMondayList();
        mondayListAdapter.notifyDataSetChanged();

    }



    private void initDatabase() {
        timetableDatabase = Room.databaseBuilder(getContext(),
                TimetableDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initMondayList() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<TimetableDataElement> entrylist = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);
                mondayList.addAll(entrylist);



            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
