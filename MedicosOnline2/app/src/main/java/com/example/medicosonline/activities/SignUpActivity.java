package com.example.medicosonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicosonline.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button signupBtn;
    TextView loginText;
    EditText emailEdit, passwordEdit, passwordCnfrmEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        passwordEdit = findViewById(R.id.password_edit_two);
        passwordCnfrmEdit = findViewById(R.id.conform_password_edit);
        emailEdit = findViewById(R.id.email_edit_two);
        loginText = findViewById(R.id.login_text);
        signupBtn = findViewById(R.id.register_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });

    }

    private void registerUser() {
        String password = passwordEdit.getText().toString();
        String passwordConfirm = passwordCnfrmEdit.getText().toString();
        String email = emailEdit.getText().toString();

        if (password.equals(passwordConfirm)){
            firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(SignUpActivity.this,"Registrtaion Successful",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                        finish();
                    }
                    else
                        Toast.makeText(SignUpActivity.this,"Registrtaion Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
            Toast.makeText(SignUpActivity.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
    }
}