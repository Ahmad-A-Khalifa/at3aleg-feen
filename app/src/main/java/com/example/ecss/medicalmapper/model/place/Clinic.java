package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Clinic /*extends RealmObject*/ implements MedicalPlace {


    public static final Creator<Clinic> CREATOR = new Creator<Clinic>() {
        @Override
        public Clinic createFromParcel(android.os.Parcel in) {
            return new Clinic(in);
        }

        @Override
        public Clinic[] newArray(int size) {
            return new Clinic[size];
        }
    };

    @SerializedName("clinic_id")
    private Integer mClinicId;

    @SerializedName("clinic_name")
    private String mClinicName;

    @SerializedName("clinic_specialization")
    private String mClinicSpecialization;

    @SerializedName("branch")
    private List<Branch> mBranches;

    public Clinic() {
    }

    public Clinic(Parcel in) {
        this.mClinicId = in.readInt();
        this.mClinicName = in.readString();
        this.mClinicSpecialization = in.readString();
        this.mBranches = new ArrayList();

        List<Branch> myList = new ArrayList();
        in.readList(myList, getClass().getClassLoader());
        this.mBranches = myList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mClinicId);
        dest.writeString(this.mClinicName);
        dest.writeString(this.mClinicSpecialization);
        dest.writeList(this.mBranches);
    }

    public Integer getClinicId() {
        return mClinicId;
    }

    public void setClinicId(Integer ClinicId) {
        this.mClinicId = ClinicId;
    }

    public String getClinicName() {
        return mClinicName;
    }

    public void setClinicName(String ClinicName) {
        this.mClinicName = ClinicName;
    }

    public String getClinicSpecialization() {
        return mClinicSpecialization;
    }

    public void setClinicSpecialization(String ClinicSpecialization) {
        this.mClinicSpecialization = ClinicSpecialization;
    }

    public List<Branch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<Branch> Branches) {
        this.mBranches = Branches;
    }
}
