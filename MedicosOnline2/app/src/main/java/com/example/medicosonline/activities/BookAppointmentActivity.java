package com.example.medicosonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicosonline.R;
import com.example.medicosonline.models.Appointment;
import com.example.medicosonline.models.Doctor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText pName, pPhoneNumber, pAge, aptDate;
    Button makeAppointment;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        pName = findViewById(R.id.p_name);
        pPhoneNumber = findViewById(R.id.p_phone);
        pAge = findViewById(R.id.p_age);
        aptDate = findViewById(R.id.apt_date);
        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        makeAppointment = findViewById(R.id.make_appointment_btn);

        makeAppointment.setOnClickListener(view -> {
            makeAppointment();
        });
    }

    private void makeAppointment() {
        String docId = getIntent().getStringExtra("DOCTORDATA");
        Doctor d = new Gson().fromJson(docId,Doctor.class);
        Appointment appointment = new Appointment("medicos"  + aptDate.getText().toString() ,firebaseAuth.getUid(),d.getUid(),pName.getText().toString(),
                pPhoneNumber.getText().toString(),aptDate.getText().toString(),Boolean.FALSE,"pending",Integer.parseInt(pAge.getText().toString()));
        db.collection("appointments").add(appointment).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(BookAppointmentActivity.this,"Appointment requested",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(BookAppointmentActivity.this,"Error writing document",Toast.LENGTH_SHORT).show();
            }
        });
//                .addOnSuccessListener( new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(BookAppointmentActivity.this,"Appointment requested",Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(BookAppointmentActivity.this,"Error writing document",Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}