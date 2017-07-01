package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ecss on 13/06/2017.
 */

public class MedicalPlaces implements Parcelable {

    @SerializedName("hospitals")
    private List<Hospital> mHospitals;

    @SerializedName("clinics")
    private List<Clinic> mClinics;

    @SerializedName("labs")
    private List<Laboratory> mLaboratories;

    @SerializedName("pharmacies")
    private List<Pharmacy> mPharmacies;

    protected MedicalPlaces(Parcel in) {
        in.readList(this.mHospitals, getClass().getClassLoader());
        in.readList(this.mClinics, getClass().getClassLoader());
        in.readList(this.mLaboratories, getClass().getClassLoader());
        in.readList(this.mPharmacies, getClass().getClassLoader());
    }

    public static final Creator<MedicalPlaces> CREATOR = new Creator<MedicalPlaces>() {
        @Override
        public MedicalPlaces createFromParcel(Parcel in) {
            return new MedicalPlaces(in);
        }

        @Override
        public MedicalPlaces[] newArray(int size) {
            return new MedicalPlaces[size];
        }
    };

    public List<Hospital> getHospitals() {
        return mHospitals;
    }

    public void setHospitals(List<Hospital> hospitals) {
        mHospitals = hospitals;
    }

    public List<Pharmacy> getPharmacies() {
        return mPharmacies;
    }

    public void setPharmacies(List<Pharmacy> pharmacies) {
        mPharmacies = pharmacies;
    }

    public List<Laboratory> getLaboratories() {
        return mLaboratories;
    }

    public void setLaboratories(List<Laboratory> laboratories) {
        mLaboratories = laboratories;
    }

    public List<Clinic> getClinics() {
        return mClinics;
    }

    public void setClinics(List<Clinic> clinics) {
        mClinics = clinics;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(mHospitals);
        dest.writeList(mClinics);
        dest.writeList(mLaboratories);
        dest.writeList(mPharmacies);
    }
}
