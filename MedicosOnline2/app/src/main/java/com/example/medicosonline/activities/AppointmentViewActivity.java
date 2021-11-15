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
import android.view.View;
import android.widget.Button;

import com.example.medicosonline.R;
import com.example.medicosonline.adapters.AppointmentAdapter;
import com.example.medicosonline.adapters.DoctorAdapter;
import com.example.medicosonline.models.Appointment;
import com.example.medicosonline.models.Doctor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AppointmentViewActivity extends AppCompatActivity {
    RecyclerView aptView;
    List<Appointment> appointments;
    AppointmentAdapter appointmentAdapter;
    FirebaseFirestore firebaseFirestore;
    private Toolbar appBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private NavigationView navigationView;
    private FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view);
        aptView = (RecyclerView)findViewById(R.id.apt_recyclerview);
        appointments = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mainDrawer = (DrawerLayout)findViewById(R.id.appointment_drawr);
        appBar =(Toolbar)findViewById(R.id.top_bar_apt);
        navigationView = (NavigationView)findViewById(R.id.navigation_view_apt);
        mauth = FirebaseAuth.getInstance();
        setupFirestore();
        setupDrawe();
        setupAppointmentView();

    }

    private void setupAppointmentView() {
        aptView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(this,appointments);
        aptView.setAdapter(appointmentAdapter);
    }

    private void setupDrawe() {
        setSupportActionBar(appBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.appointment_btn){
                startActivity(new Intent(getApplicationContext(),BookAppointmentActivity.class));
                mainDrawer.closeDrawers();
                return true;
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
        firebaseFirestore.collection("appointments").whereEqualTo("uid",mauth.getCurrentUser().getUid())
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && !task.getResult().isEmpty()){
                    appointments.addAll(task.getResult().toObjects(Appointment.class));
                    appointmentAdapter.notifyDataSetChanged();
                }
                else{
                    Log.d("DOCTORDATA",String.valueOf(task.getException()));
                }
            }
        });
    }
}