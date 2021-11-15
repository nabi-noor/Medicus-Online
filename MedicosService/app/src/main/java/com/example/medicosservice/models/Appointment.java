package com.example.medicosservice.models;

public class Appointment {
    String aptId , uid, doctorId, name, phoneNumber,date,status;
    Boolean reviewed;
    int age;

    public Appointment(){}

    public Appointment(String aptId, String uid, String doctorId, String name, String phoneNumber, String date, Boolean reviewed, String status, int age) {
        this.aptId = aptId;
        this.uid = uid;
        this.doctorId = doctorId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.reviewed = reviewed;
        this.status = status;
        this.age = age;
    }

    public String getAptId() {
        return aptId;
    }

    public void setAptId(String aptId) {
        this.aptId = aptId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getReviewed() {
        return reviewed;
    }

    public void setReviewed(Boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "aptId='" + aptId + '\'' +
                ", uid='" + uid + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date='" + date + '\'' +
                ", reviewed=" + reviewed +
                ", status=" + status +
                ", age=" + age +
                '}';
    }
}
