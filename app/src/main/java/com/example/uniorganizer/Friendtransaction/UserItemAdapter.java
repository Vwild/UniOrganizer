package com.example.uniorganizer.Friendtransaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniorganizer.R;

import java.util.ArrayList;


public class UserItemAdapter extends ArrayAdapter<User> {

    private ArrayList<User> users;
    private Context context;

    public UserItemAdapter(Context context, ArrayList<User> users ) {
        super(context, R.layout.user_entry_item , users);
        this.users = users ;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.user_entry_item, null);
        }

        TextView username = v.findViewById(R.id.textView_username_entry_item);
        ImageView symbol = v.findViewById(R.id.symbolPresent);

        User user = users.get(position);
        if (user == null){
            Toast.makeText(context,"user null",Toast.LENGTH_SHORT).show();
        }else if (username == null){
            Toast.makeText(context,"textview null",Toast.LENGTH_SHORT).show();
        }
        username.setText(user.getUsername());
        if(user.isPresentAtUni()){
            symbol.setImageResource(R.drawable.ic_school_green_24dp);
        }else {
            symbol.setImageResource(R.drawable.ic_school_red_24dp);
        }

        return v;
    }
}
