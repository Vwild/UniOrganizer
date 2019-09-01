package com.example.uniorganizer.Stundenplan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.uniorganizer.R;

public class DayFragment extends Fragment {

    private TextView textView;
    private ListView listView;

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

        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
