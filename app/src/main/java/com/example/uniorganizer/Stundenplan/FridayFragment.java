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

public class FridayFragment extends Fragment {
    public static final String DATABASE_NAME = "Stundenplan";


    private TextView textView;
    private ListView listView;
    private ArrayList<TimetableDataElement> fridayList;
    private TimetableEntryItemAdapter fridayListAdapter;
    private TimetableDatabase timetableDatabase;
    private String weekday = "Friday";

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
        fridayList = new ArrayList<>();
        fridayListAdapter = new TimetableEntryItemAdapter(getContext(), fridayList);
        listView.setAdapter(fridayListAdapter);

        textView.setText("Friday");


        return layout;
    }
    private void setOnClickListener(){
        buttonSwitchFragmentBackward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.activityMainFragmentContainer, new ThursdayFragment()).addToBackStack(null).commit();
            }
        });
        buttonSwitchFragmentForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.activityMainFragmentContainer, new DayFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        fridayList.clear();
        initFridayList();
        fridayListAdapter.notifyDataSetChanged();

    }



    private void initDatabase() {
        timetableDatabase = Room.databaseBuilder(getContext(),
                TimetableDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    private void initFridayList() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<TimetableDataElement> entrylist = timetableDatabase.daoAccess().findLecturesByWeekday(weekday);
                fridayList.addAll(entrylist);



            }
        }).start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
