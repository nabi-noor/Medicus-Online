package com.example.medicosonline.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.medicosonline.R;
import com.example.medicosonline.activities.fragments.DoctorInfoFragment;
import com.example.medicosonline.activities.fragments.ReviewFragment;
import com.example.medicosonline.adapters.ViewPagerAdapter;
import com.example.medicosonline.models.Doctor;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

public class DoctorActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private ImageView profilePicture;
    private TextView docName;
    private Button bookAptBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        profilePicture = findViewById(R.id.doc_profile_pic);
        docName = findViewById(R.id.doc_name);
        tableLayout = (TabLayout)findViewById(R.id.tablayout);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        tableLayout.setupWithViewPager(viewPager);
        bookAptBtn = findViewById(R.id.bookapt_btn);
        String json = getIntent().getStringExtra("DOCTORDATA");
        Log.d("DOCTORDATA",json);
        Doctor d = new Gson().fromJson(json,Doctor.class);
        setUpView(d);
        bookAptBtn.setOnClickListener(view -> {
            startActivity(new Intent(DoctorActivity.this, BookAppointmentActivity.class).putExtra("DOCTORDATA",new Gson().toJson(d)));
        });
    }

    private void setUpView(Doctor doctor) {
        docName.setText(doctor.getName());
        setupViewPager(doctor);
    }

    private void setupViewPager(Doctor doctor) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new DoctorInfoFragment(doctor),"Doctor Info");
        viewPagerAdapter.addFragment(new ReviewFragment(),"Reviews");
        viewPager.setAdapter(viewPagerAdapter);
    }
}