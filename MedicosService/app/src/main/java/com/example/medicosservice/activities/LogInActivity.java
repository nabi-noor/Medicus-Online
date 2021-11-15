package com.example.medicosservice.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicosservice.R;
import com.example.medicosservice.models.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.util.List;

public class LogInActivity extends AppCompatActivity {
    FirebaseFirestore db;
    EditText emailEdit, passwordEdit;
    Button loginBtn;
    Boolean userExit = false;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        db = FirebaseFirestore.getInstance();
        emailEdit = findViewById(R.id.email_edit);
        passwordEdit = findViewById(R.id.password_edit);
        loginBtn = findViewById(R.id.login_btn);
        firebaseAuth = FirebaseAuth.getInstance();
        loginBtn.setOnClickListener(view -> {
            String email, password;
            email = emailEdit.getText().toString();
            password = passwordEdit.getText().toString();
            if (!email.equals("") && !password.equals("")){
                userExits(email,password);
            }
            else if (email.equals("") && password.equals("")){
                emailEdit.setError("Empty!");
                passwordEdit.setError("Empty!");
            }
            else if (password.equals("")){
                passwordEdit.setError("Empty!");
            }
            else{
                emailEdit.setError("Empty!");
            }
        });
    }

    private void doctorSignIn(String email, String password) {

            firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(LogInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LogInActivity.this,MainActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(LogInActivity.this,"You are not registered doctor",Toast.LENGTH_SHORT).show();
                }
            });
    }

    private void userExits(String email,String password) {
        db.collection("doctors").whereEqualTo("email",email)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && !task.getResult().isEmpty()){
                    Log.d("DOCTORDATA",task.toString());
                    doctorSignIn(email,password);
                }
                else{
                    Log.d("DOCTORDATA",String.valueOf(task.getException()));
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null){
            Toast.makeText(LogInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LogInActivity.this,MainActivity.class));
        }
    }
}