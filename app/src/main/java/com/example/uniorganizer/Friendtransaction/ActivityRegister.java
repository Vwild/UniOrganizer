package com.example.uniorganizer.Friendtransaction;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.uniorganizer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ActivityRegister extends AppCompatActivity {

    private Button buttonRegister;
    private EditText editTextPassword , editTextUsername , editTextEmail ;

    FirebaseAuth account;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
        account = FirebaseAuth.getInstance();
    }

    private void findViews (){
        buttonRegister = findViewById(R.id.button_register);
        editTextPassword = findViewById(R.id.editText_enter_password);
        editTextUsername = findViewById(R.id.editText_enter_username);
        editTextEmail = findViewById(R.id.editText_enter_email);
    }

    private void safeUserData (){
        String Username = editTextUsername.getText().toString();
        String Password = editTextPassword.getText().toString();
        String Email = editTextEmail.getText().toString();
        account.createUserWithEmailAndPassword(Email,Password);
    }
}
