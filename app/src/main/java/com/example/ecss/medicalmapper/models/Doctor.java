package com.example.ecss.medicalmapper.models;

import java.util.ArrayList;



public class Doctor extends User {
    private DoctorInfo info;
    private ArrayList<Clinic> clinics;
    private ArrayList<Hospital> hospitals;
    private ArrayList<Laboratory> laboratories;
    private ArrayList<Pharmacy> pharmacies;

    public Doctor() {
        this(new DoctorInfo());
    }

    public Doctor(DoctorInfo info) {
        this.type = User.TYPE_DOCTOR_USER;
        this.info = info;
        this.clinics = new ArrayList<>();
        this.hospitals = new ArrayList<>();
        this.laboratories = new ArrayList<>();
        this.pharmacies = new ArrayList<>();
    }

    public DoctorInfo getInfo() {
        return info;
    }

    public void setInfo(DoctorInfo info) {
        this.info = info;
    }

    public ArrayList<Clinic> getClinics() {
        return clinics;
    }

    public void setClinics(ArrayList<Clinic> clinics) {
        this.clinics = clinics;
    }

    public ArrayList<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(ArrayList<Hospital> hospitals) {
        this.hospitals = hospitals;
    }

    public ArrayList<Laboratory> getLaboratories() {
        return laboratories;
    }

    public void setLaboratories(ArrayList<Laboratory> laboratories) {
        this.laboratories = laboratories;
    }

    public ArrayList<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public void setPharmacies(ArrayList<Pharmacy> pharmacies) {
        this.pharmacies = pharmacies;
    }
}
