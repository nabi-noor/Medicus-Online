package com.example.medicosservice.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.LayoutDirection;
import android.util.Log;
import android.view.MenuItem;

import com.example.medicosservice.R;
import com.example.medicosservice.adapters.AppointmentAdapter;
import com.example.medicosservice.models.Appointment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView aptRecyclerView;
    List<Appointment> appointments;
    AppointmentAdapter appointmentAdapter;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mauth;
    private Toolbar appBar;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout mainDrawer;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aptRecyclerView = findViewById(R.id.apt_recyclerview);
        appointments = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        mauth = FirebaseAuth.getInstance();
        mainDrawer = (DrawerLayout)findViewById(R.id.main_drawr);
        appBar =(Toolbar)findViewById(R.id.top_bar);
        navigationView = (NavigationView)findViewById(R.id.navigation_view);
        setupFireStore();
        setupDrawer();
        setupDoctorVire();
    }

    private void setupDrawer() {
        setSupportActionBar(appBar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,mainDrawer,R.string.app_name,R.string.app_name);
        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.logout_btn){
                mauth.signOut();
                startActivity(new Intent(MainActivity.this,LogInActivity.class));
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
    private void setupDoctorVire() {

        firebaseFirestore.collection("appointments").whereEqualTo("doctorId",mauth.getCurrentUser().getUid())
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

    private void setupFireStore() {
        aptRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        appointmentAdapter = new AppointmentAdapter(this,appointments);
        aptRecyclerView.setAdapter(appointmentAdapter);
    }
}