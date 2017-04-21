package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class Pharmacy extends RealmObject implements MedicalPlace {


    public static final Creator<Pharmacy> CREATOR = new Creator<Pharmacy>() {
        @Override
        public Pharmacy createFromParcel(Parcel source) {
            return new Pharmacy(source);
        }

        @Override
        public Pharmacy[] newArray(int size) {
            return new Pharmacy[size];
        }
    };
    @SerializedName("Branch")
    RealmList<Branch> mBranches;
    @SerializedName("pharmacy_id")
    private Integer mPharmacyId;
    @SerializedName("pharmacy_name")
    private String mPharmacyName;
    @SerializedName("pharmacy_hot_line")
    private String mPharmacyHotline;
    @SerializedName("pharmacy_delivery")
    private Boolean mPharmacyDelivery;

    public Pharmacy() {
    }

    public Pharmacy(Parcel in) {
        this.mPharmacyId = in.readInt();
        this.mPharmacyName = in.readString();
        this.mPharmacyHotline = in.readString();
        this.mPharmacyDelivery = in.readByte() != 0;

        RealmList<Branch> myList = null;
        in.readList(myList, List.class.getClassLoader());
        this.setmBranches(myList);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mPharmacyId);
        dest.writeString(this.mPharmacyName);
        dest.writeString(this.mPharmacyHotline);
        dest.writeByte((byte) (this.mPharmacyDelivery ? 1 : 0));
        dest.writeList(this.mBranches);
    }

    public Integer getmPharmacyId() {
        return mPharmacyId;
    }

    public void setmPharmacyId(Integer mPharmacyId) {
        this.mPharmacyId = mPharmacyId;
    }

    public String getmPharmacyName() {
        return mPharmacyName;
    }

    public void setmPharmacyName(String mPharmacyName) {
        this.mPharmacyName = mPharmacyName;
    }

    public String getmPharmacyHotline() {
        return mPharmacyHotline;
    }

    public void setmPharmacyHotline(String mPharmacyHotline) {
        this.mPharmacyHotline = mPharmacyHotline;
    }

    public Boolean getmPharmacyDelivery() {
        return mPharmacyDelivery;
    }

    public void setmPharmacyDelivery(Boolean mPharmacyDelivery) {
        this.mPharmacyDelivery = mPharmacyDelivery;
    }

    public RealmList<Branch> getmBranches() {
        return mBranches;
    }

    public void setmBranches(RealmList<Branch> mBranches) {
        this.mBranches = mBranches;
    }
}
