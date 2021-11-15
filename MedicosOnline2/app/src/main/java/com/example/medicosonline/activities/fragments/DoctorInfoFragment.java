package com.example.medicosonline.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.medicosonline.R;
import com.example.medicosonline.models.Doctor;

import java.util.Objects;

public class DoctorInfoFragment extends Fragment {
    Doctor doctor;
    TextView qualification, rating, fees, desc, location;
    public DoctorInfoFragment(Doctor doctor){
        this.doctor = doctor;
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_info, container, false);
        qualification = view.findViewById(R.id.frag_qualification);
        rating = view.findViewById(R.id.rating_frag);
        fees = view.findViewById(R.id.fees_frag);
        desc = view.findViewById(R.id.desc_frag);
        location = view.findViewById(R.id.location_frag);
        setupView();
        return view;
    }
    void setupView(){
        qualification.setText("Qualification: " + doctor.getQualification());
        rating.setText("Rating: " +String.valueOf(doctor.getRating()));
        fees.setText("Fees: "+String.valueOf(doctor.getFees()));
        desc.setText("Description: \n"+doctor.getDescription());
        location.setText("Location: "+doctor.getLocation());
    }
}