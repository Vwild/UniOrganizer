package com.example.uniorganizer.Friendtransaction;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uniorganizer.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private TextView register;
    private Button loginButton;
    private ProgressDialog dialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        setOnClicklistener();
        firebaseAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(ActivityLogin.this);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
        }
    }

    private void findViews() {
        editTextEmail = findViewById(R.id.editText_enter_email);
        editTextPassword = findViewById(R.id.editText_enter_password);
        register = findViewById(R.id.textView_register_link);
        loginButton = findViewById(R.id.button_login);
    }

    private void setOnClicklistener() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setMessage("Please wait");
                dialog.show();
                loginAccount();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
                startActivity(intent);
            }
        });
    }

    private void loginAccount() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            dialog.dismiss();
                            Toast.makeText(ActivityLogin.this,"Logged in",Toast.LENGTH_SHORT);
                            finish();
                        }else{
                            dialog.dismiss();
                            Toast.makeText(ActivityLogin.this,"Login failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
