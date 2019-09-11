package com.example.uniorganizer.Friendtransaction;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uniorganizer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


public class FriendsActivity extends AppCompatActivity {

    private static final String KEY_PRESENT_AT_UNI = "presentAtUni";

    private Button buttonBack ,buttonSearch;
    private EditText editTextSearch;
    private ListView listViewUsers;

    private DatabaseReference reference;
    private ArrayList<User> users ;
    private UserItemAdapter adapter;
    private User myAccount;
    private boolean myPresenceStatus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        checkLogin();
        init();
        displayUsers();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (FirebaseAuth.getInstance().getCurrentUser() == null){
            finish();
        }else{
            getMyPresenceStatus();
            invalidateOptionsMenu();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.friendsactivity_menu,menu);
        if(!myPresenceStatus){
            menu.findItem(R.id.menu_item_present_at_uni).setChecked(false);
        }else {
            menu.findItem(R.id.menu_item_present_at_uni).setChecked(true);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_logout :
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.menu_item_delete_account :
                deleteAccount();
                return true;
            case  R.id.menu_item_present_at_uni :
                if(item.isChecked()) {
                    changePresenceStatus(false);
                    item.setChecked(false);
                    return true;
                }else if (item.getItemId()==R.id.menu_item_present_at_uni&&!item.isChecked()){
                    changePresenceStatus(true);
                    item.setChecked(true);
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
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
                finish();
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findUsersbyName();
            }
        });
    }

    private void checkLogin(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null){
            Intent intent = new Intent(FriendsActivity.this, ActivityLogin.class);
            startActivity(intent);
        }else {
            getMyPresenceStatus();
        }
    }


    private void displayUsers() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                users.clear();
                for (DataSnapshot allUsers: dataSnapshot.getChildren()) {
                    users.add(allUsers.getValue(User.class));
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void findUsersbyName() {
        String username = editTextSearch.getText().toString().trim();
        if(username.contentEquals("")){
            displayUsers();
        }else {
            reference.orderByChild("Username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    users.clear();
                    for (DataSnapshot allUsers : dataSnapshot.getChildren()) {
                        users.add(allUsers.getValue(User.class));
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }

    }

    private void getMyPresenceStatus (){
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myAccount = dataSnapshot.getValue(User.class);
                if(myAccount!=null){
                    myPresenceStatus = myAccount.isPresentAtUni();
                }
                invalidateOptionsMenu();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void changePresenceStatus(boolean present){
        HashMap<String,Object> map = new HashMap<>();
        map.put(KEY_PRESENT_AT_UNI,present);
        reference.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
    }

    private void deleteAccount (){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String id = firebaseUser.getUid();
        FirebaseAuth.getInstance().getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    reference.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                finish();
                            }
                        }
                    });
                }else{
                    Toast.makeText(FriendsActivity.this,"Delete failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
