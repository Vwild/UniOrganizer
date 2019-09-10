package com.example.uniorganizer.Friendtransaction;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uniorganizer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class FriendsActivity extends AppCompatActivity {

    Button buttonBack ,buttonSearch;
    EditText editTextSearch;
    ListView listViewUsers;
    Switch switchPresent;

    private DatabaseReference reference;
    private ArrayList<User> users ;
    private UserItemAdapter adapter;
    private User myAccount;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        checkLogin();
        init();
        displayUsers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friendsactivity_menu,menu);
        switchPresent = (Switch) findViewById(R.id.switchItem);
        switchPresent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myAccount = dataSnapshot.getValue(User.class);
                if (! myAccount.isPresentAtUni()){
                    Toast.makeText(FriendsActivity.this,myAccount.getUsername(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return true;
    }

    private void init(){
        findViews();
        setOnClicklistener();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        users = new ArrayList<>();
        adapter = new UserItemAdapter(this,users);
        listViewUsers.setAdapter(adapter);
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


    private void displayUsers() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot allUsers: dataSnapshot.getChildren()) {
                    users.add(allUsers.getValue(User.class));
                    Toast.makeText(FriendsActivity.this,"updated",Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
