package com.example.uniorganizer.Friendtransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uniorganizer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ActivityRegister extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextPassword , editTextUsername , editTextEmail ;
    private ProgressBar cycle;

    FirebaseAuth firebaseAuth;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        setOnClicklistener();
        firebaseAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("Users");
    }

    private void setOnClicklistener() {
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cycle.setVisibility(View.VISIBLE);
                createAccount();
            }
        });
    }

    private void findViews (){
        buttonRegister = findViewById(R.id.button_register);
        editTextPassword = findViewById(R.id.editText_set_password);
        editTextUsername = findViewById(R.id.editText_set_username);
        editTextEmail = findViewById(R.id.editText_set_email);
        cycle = findViewById(R.id.progressBar_cycle_register);
    }

    private void createAccount (){
        final String Username = editTextUsername.getText().toString();
        String Password = editTextPassword.getText().toString();
        final String Email = editTextEmail.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(Username).build();
                    user.updateProfile(userProfileChangeRequest)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        cycle.setVisibility(View.INVISIBLE);
                                        Toast.makeText(ActivityRegister.this,"Registered",Toast.LENGTH_SHORT).show();
                                        createDBentry(Username,Email);
                                    }

                                }
                            });
                } else {
                    cycle.setVisibility(View.INVISIBLE);
                    Toast.makeText(ActivityRegister.this,"Failed, please try again",Toast.LENGTH_SHORT);
                }

            }
    });
    }

    private void createDBentry(String username,String email) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        String UserId = user.getUid();
        User userobject = new User(email,username);
        reference.child(UserId).setValue(userobject);
        reference.child(UserId).child("Username").setValue(username).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    finish();
                }else {
                    Toast.makeText(ActivityRegister.this,"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
