package com.example.medicosonline.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicosonline.R;
import com.example.medicosonline.activities.CallActivity;
import com.example.medicosonline.models.Appointment;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {
    Context context;
    List<Appointment> appointments;

    public AppointmentAdapter(Context context, List<Appointment> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.appointment_card,parent,false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        holder.pName.setText("Mr/Ms/Mrs" + appointments.get(position).getName());
        holder.aptDate.setText("Appointment Date: "+appointments.get(position).getDate());
        holder.aptStatus.setText("Appointment Status: " +appointments.get(position).getStatus());
        holder.viewAptBtn.setOnClickListener(view -> {
            Intent i = new Intent(context, CallActivity.class);
            i.putExtra("username",appointments.get(position).getDoctorId());
            context.startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {
        final TextView pName, aptDate, aptStatus;
        final Button viewAptBtn;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            pName = itemView.findViewById(R.id.p_name);
            aptDate = itemView.findViewById(R.id.apt_date);
            aptStatus = itemView.findViewById(R.id.apt_status);
            viewAptBtn = itemView.findViewById(R.id.view_apt);
        }
    }
}
