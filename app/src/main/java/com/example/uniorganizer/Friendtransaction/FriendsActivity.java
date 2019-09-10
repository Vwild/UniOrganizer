package com.example.uniorganizer.Friendtransaction;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniorganizer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;


public class FriendsActivity extends AppCompatActivity {

    Button buttonBack ,buttonSearch;
    EditText editTextSearch;
    ListView listViewUsers;

    private ArrayList<User> users ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        findViews();
        setOnClicklistener();
        checkLogin();
    }

    private void findViews() {
        buttonBack = findViewById(R.id.button_back);
        buttonSearch = findViewById(R.id.button_search_friend);
        editTextSearch = findViewById(R.id.editText_search_friend);
        listViewUsers = findViewById(R.id.listView_users);
    }

    private void setOnClicklistener() {

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do ...
            }
        });
    }

    private void checkLogin(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            Intent intent = new Intent(FriendsActivity.this, ActivityLogin.class);
            startActivity(intent);
        }
    }
}
