package com.example.uniorganizer.Stundenplan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.uniorganizer.R;

import java.util.List;

public class TimetableEntryItemAdapter extends ArrayAdapter<TimetableEntry> {

    private List<TimetableEntry> timetableEntries;
    private Context context;

    public TimetableEntryItemAdapter (Context context, List<TimetableEntry> timetableEntries){
        super(context, R.layout.timetable_entry_item,timetableEntries);
        this.context = context;
        this.timetableEntries = timetableEntries;
    }


    @Override
    public View getView(int position,View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.timetable_entry_item, null);
        }

        TextView title = v.findViewById(R.id.textView_entry_item_title);
        TextView timeperiod = v.findViewById(R.id.textView_entry_item_timeperiod);
        TextView description = v.findViewById(R.id.textView_entry_item_description);
        TimetableEntry entry = timetableEntries.get(position);

        title.setText(entry.getTitle());
        timeperiod.setText(entry.getTimePeriod());
        description.setText(entry.getDescription());

        return v;
    }
}
