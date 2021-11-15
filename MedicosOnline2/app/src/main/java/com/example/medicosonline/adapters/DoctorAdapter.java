package com.example.medicosonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicosonline.R;
import com.example.medicosonline.activities.BookAppointmentActivity;
import com.example.medicosonline.activities.DoctorActivity;
import com.example.medicosonline.models.Doctor;
import com.google.gson.Gson;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>{

    private final Context context;
    private final List<Doctor> doctorDetalis;
    public DoctorAdapter(Context context, List<Doctor> doctorDetalis) {
        this.context = context;
        this.doctorDetalis = doctorDetalis;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.doctor_card_layout,parent,false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.doctorName.setText("Dr " +doctorDetalis.get(position).getName());
        holder.docQualification.setText(doctorDetalis.get(position).getQualification());
        holder.drAddress.setText(doctorDetalis.get(position).getLocation());
        holder.experiace.setText("Experiance "+String.valueOf(doctorDetalis.get(position).getExperiance()));
        holder.fees.setText("Fees "+String.valueOf(doctorDetalis.get(position).getFees()));
        holder.rating.setText("Rating: " +String.valueOf(doctorDetalis.get(position).getRating()));
        holder.viewProfile.setOnClickListener(view -> {
            Intent i = new Intent(context, DoctorActivity.class);
            Doctor d = doctorDetalis.get(position);
            String json = new Gson().toJson(d);
            i.putExtra("DOCTORDATA",json);
            context.startActivity(i);
        });
        holder.bookAptBtn.setOnClickListener(view -> {
            Doctor d = doctorDetalis.get(position);
            context.startActivity(new Intent(context, BookAppointmentActivity.class).putExtra("DOCTORDATA",new Gson().toJson(d)));
        });
    }

    @Override
    public int getItemCount() {
        return doctorDetalis.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        final ImageView docProfile;
        final TextView doctorName;
        final TextView docQualification, drAddress, viewProfile,fees,rating,experiace;
        Button bookAptBtn;
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            docProfile = (ImageView) itemView.findViewById(R.id.dr_profile_pic);
            doctorName = (TextView) itemView.findViewById(R.id.dr_name);
            docQualification = (TextView) itemView.findViewById(R.id.dr_catagory);
            fees = (TextView) itemView.findViewById(R.id.fees_view);
            rating = (TextView) itemView.findViewById(R.id.rating_view);
            experiace = (TextView) itemView.findViewById(R.id.experiace_view);
            viewProfile = (TextView) itemView.findViewById(R.id.view_profie);
            drAddress = (TextView) itemView.findViewById(R.id.dr_address);
            bookAptBtn = (Button) itemView.findViewById(R.id.book_apt_btn);
        }
    }
}
