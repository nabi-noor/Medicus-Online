package com.example.medicosonline.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.medicosonline.R;
import com.example.medicosonline.adapters.DoctorAdapter;
import com.example.medicosonline.models.Doctor;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView doctorsView;
    List<Doctor> doctorDetalis;
    DoctorAdapter doctorAdapter;
    FirebaseFirestore firebaseFirestore;
    private Toolbar appBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private NavigationView navigationView;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        doctorsView = (RecyclerView)findViewById(R.id.doctor_recyclerview);
        doctorDetalis = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mainDrawer = (DrawerLayout)findViewById(R.id.main_drawer);
        appBar =(Toolbar)findViewById(R.id.top_bar);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        mauth = FirebaseAuth.getInstance();
        setupFirestore();
        setupDrawe();
        setupDoctorsView();
    }

    private void setupDrawe() {
        setSupportActionBar(appBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.appointment_btn){
                startActivity(new Intent(getApplicationContext(),AppointmentViewActivity.class));
                mainDrawer.closeDrawers();
                return true;
            }
            else if(item.getItemId() == R.id.logout_btn){
                mauth.signOut();
                startActivity(new Intent(MainActivity.this,LoginIntroActivity.class));
                finish();
            }
            return false;
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void setupFirestore() {
        CollectionReference collectionReference = firebaseFirestore.collection("doctors");
        collectionReference.addSnapshotListener((value, error) -> {
            if (value == null || error != null){
                Log.d("ErrorFirebase",error.getMessage());
                return;
            }
            doctorDetalis.addAll(value.toObjects(Doctor.class));
            doctorAdapter.notifyDataSetChanged();
        });
    }

    private void setupDoctorsView() {
        doctorsView.setLayoutManager(new LinearLayoutManager(this));
        doctorAdapter = new DoctorAdapter(this,doctorDetalis);
        doctorsView.setAdapter(doctorAdapter);
    }
}