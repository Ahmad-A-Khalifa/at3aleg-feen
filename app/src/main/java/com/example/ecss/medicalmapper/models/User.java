package com.example.ecss.medicalmapper.models;


import java.util.ArrayList;
;

public class User {
    protected String id;
    protected String name;
    protected String email;
    protected String password;
    protected String type;
    protected ArrayList<MedicalPlace> savedPlaces;
    public static final String TYPE_NORMAL_USER = "Normal user";
    public static final String TYPE_DOCTOR_USER = "Doctor user";

    protected User() {
    }

    public static Doctor switchToDoctor(User user, DoctorInfo doctorInfo) {
        if (user instanceof NormalUser) {
            Doctor doctor = new Doctor(doctorInfo);
            doctor.setID(user.getID());
            doctor.setName(user.getName());
            doctor.setEmail(user.getEmail());
            doctor.setPassword(user.getPassword());
            return doctor;
        } else {
            return (Doctor) user;
        }
    }

    protected String getID() {
        return id;
    }

    protected String getName() {
        return name;
    }

    protected String getEmail() {
        return email;
    }

    protected String getPassword() {
        return password;
    }

    protected String getType() {
        return type;
    }

    protected void setID(String id) {
        this.id = id;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected void setType(String type) {
        this.type = type;
    }

    public ArrayList<MedicalPlace> getSavedPlaces() {
        return savedPlaces;
    }

    public void setSavedPlaces(ArrayList<MedicalPlace> savedPlaces) {
        this.savedPlaces = savedPlaces;
    }
}
