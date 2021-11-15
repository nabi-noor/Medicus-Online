package com.example.medicosservice.models;

public class Doctor {
    String uid, name, qualification
            ,location, description, profilePicture;
    double phoneNumber;
    float rating;
    int experiance, fees;


    public Doctor(String uid, String name, String qualification, String location, String description, String profilePicture, double phoneNumebr, float rating) {
        this.uid = uid;
        this.name = name;
        this.qualification = qualification;
        this.location = location;
        this.description = description;
        this.profilePicture = profilePicture;
        this.phoneNumber = phoneNumebr;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", qualification='" + qualification + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", phoneNumebr=" + phoneNumber +
                ", rating=" + rating +
                '}';
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(double phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Doctor() {
    }

    public int getExperiance() {
        return experiance;
    }

    public void setExperiance(int experiance) {
        this.experiance = experiance;
    }

    public int getFees() {
        return fees;
    }

    public void setFees(int fees) {
        this.fees = fees;
    }
}
