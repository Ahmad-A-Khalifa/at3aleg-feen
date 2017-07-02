package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Branch implements Parcelable/*extends RealmObject*/ {

    public static final Creator<Branch> CREATOR = new Creator<Branch>() {
        @Override
        public Branch createFromParcel(android.os.Parcel in) {
            return new Branch(in);
        }

        @Override
        public Branch[] newArray(int size) {
            return new Branch[size];
        }
    };

    @SerializedName("branch_id")
    private Integer mBranchId;

    @SerializedName("branch_type")
    private String mBranchType;

    @SerializedName("branch_street_name")
    private String mBranchStreetName;

    @SerializedName("branch_building_num")
    private String mBranchBuildingNum;

    @SerializedName("branch_floor_num")
    private String mBranchFloorNum;

    @SerializedName("branch_apartment_num")
    private String mBranchAppartmentNum;

    @SerializedName("branch_address_notes")
    private String mBranchAddressNotes;

    @SerializedName("branch_phone_num")
    private String mBranchPhoneNum;

    @SerializedName("branch_longitude")
    private String mBranchLongitude;

    @SerializedName("branch_latitude")
    private String mBranchLatitude;

    @SerializedName("branch_rate")
    private String mBranchRate;

    @SerializedName("_appointments")
    private String mAppointments;

    @SerializedName("branch_language")
    private Integer mBranchLanguage;

    public Branch() {

    }

    public Branch(Parcel in) {
        this.mBranchId = in.readInt();
        this.mBranchType = in.readString();
        this.mBranchStreetName = in.readString();
        this.mBranchBuildingNum = in.readString();
        this.mBranchFloorNum = in.readString();
        this.mBranchAppartmentNum = in.readString();
        this.mBranchAddressNotes = in.readString();
        this.mBranchPhoneNum = in.readString();
        this.mBranchLongitude = in.readString();
        this.mBranchLatitude = in.readString();
        this.mBranchRate = in.readString();
        this.mAppointments = in.readString();
        this.mBranchLanguage = in.readInt();
    }

    /*public Branch(BranchBuilder branchBuilder) {
        this.mBranchId = branchBuilder.mBranchId;
        this.mBranchType = branchBuilder.mBranchType;
        this.mBranchStreetName = branchBuilder.mBranchStreetName;
        this.mBranchBuildingNum = branchBuilder.mBranchBuildingNum;
        this.mBranchFloorNum = branchBuilder.mBranchFloorNum;
        this.mBranchAppartmentNum = branchBuilder.mBranchAppartmentNum;
        this.mBranchAddressNotes = branchBuilder.mBranchAddressNotes;
        this.mBranchPhoneNum = branchBuilder.mBranchPhoneNum;
        this.mBranchLongitude = branchBuilder.mBranchLongitude;
        this.mBranchLatitude = branchBuilder.mBranchLatitude;
        this.mBranchRate = branchBuilder.mBranchRate;
        this.mAppointments = branchBuilder.mAppointments;
        this.mBranchLanguage = branchBuilder.mBranchLanguage;
    }*/

    public void setBranchId(Integer mBranchId) {
        this.mBranchId = mBranchId;
    }

    public void setBranchType(String mBranchType) {
        this.mBranchType = mBranchType;
    }

    public void setBranchStreetName(String mBranchStreetName) {
        this.mBranchStreetName = mBranchStreetName;
    }

    public void setBranchBuildingNum(String mBranchBuildingNum) {
        this.mBranchBuildingNum = mBranchBuildingNum;
    }

    public void setBranchFloorNum(String mBranchFloorNum) {
        this.mBranchFloorNum = mBranchFloorNum;
    }

    public void setBranchAppartmentNum(String mBranchAppartmentNum) {
        this.mBranchAppartmentNum = mBranchAppartmentNum;
    }

    public void setBranchAddressNotes(String mBranchAddressNotes) {
        this.mBranchAddressNotes = mBranchAddressNotes;
    }

    public void setBranchPhoneNum(String mBranchPhoneNum) {
        this.mBranchPhoneNum = mBranchPhoneNum;
    }

    public void setBranchLongitude(String mBranchLongitude) {
        this.mBranchLongitude = mBranchLongitude;
    }

    public void setBranchLatitude(String mBranchLatitude) {
        this.mBranchLatitude = mBranchLatitude;
    }

    public void setBranchRate(String mBranchRate) {
        this.mBranchRate = mBranchRate;
    }

    public void setAppointments(String mAppointments) {
        this.mAppointments = mAppointments;
    }

    public void setBranchLanguage(Integer mBranchLanguage) {
        this.mBranchLanguage = mBranchLanguage;
    }

    public int getBranchId() {
        return mBranchId;
    }

    public String getBranchType() {
        return mBranchType;
    }

    public String getBranchStreetName() {
        return mBranchStreetName;
    }

    public String getBranchBuildingNum() {
        return mBranchBuildingNum;
    }

    public String getBranchFloorNum() {
        return mBranchFloorNum;
    }

    public String getBranchAppartmentNum() {
        return mBranchAppartmentNum;
    }

    public String getBranchAddressNotes() {
        return mBranchAddressNotes;
    }

    public String getBranchPhoneNum() {
        return mBranchPhoneNum;
    }

    public String getBranchLongitude() {
        return mBranchLongitude;
    }

    public String getBranchLatitude() {
        return mBranchLatitude;
    }

    public String getBranchRate() {
        return mBranchRate;
    }

    public String getAppointments() {
        return mAppointments;
    }

    public Integer getBranchLanguage() {
        return mBranchLanguage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.mBranchId);
        dest.writeString(this.mBranchType);
        dest.writeString(this.mBranchStreetName);
        dest.writeString(this.mBranchBuildingNum);
        dest.writeString(this.mBranchFloorNum);
        dest.writeString(this.mBranchAppartmentNum);
        dest.writeString(this.mBranchAddressNotes);
        dest.writeString(this.mBranchPhoneNum);
        dest.writeString(this.mBranchLongitude);
        dest.writeString(this.mBranchLatitude);
        dest.writeString(this.mBranchRate);
        dest.writeString(this.mAppointments);
        dest.writeInt(this.mBranchLanguage);
    }


/*    public static class BranchBuilder {


        private Integer mBranchId;
        private String mBranchType;
        private String mBranchStreetName;
        private String mBranchBuildingNum;
        private String mBranchFloorNum;
        private String mBranchAppartmentNum;
        private String mBranchAddressNotes;
        private String mBranchPhoneNum;
        private String mBranchLongitude;
        private String mBranchLatitude;
        private String mBranchRate;
        private String mAppointments;
        private Integer mBranchLanguage;

        public BranchBuilder branchId(Integer branchId) {
            mBranchId = branchId;
            return this;
        }

        public BranchBuilder branchType(String branchType) {
            mBranchType = branchType;
            return this;
        }

        public BranchBuilder branchStreetName(String branchStreetName) {
            mBranchStreetName = branchStreetName;
            return this;
        }

        public BranchBuilder branchBuildingNum(String branchBuildingNum) {
            mBranchBuildingNum = branchBuildingNum;
            return this;
        }

        public BranchBuilder branchFloorNum(String branchFloorNum) {
            mBranchFloorNum = branchFloorNum;
            return this;
        }

        public BranchBuilder branchAppartmentNum(String branchAppartmentNum) {
            mBranchAppartmentNum = branchAppartmentNum;
            return this;
        }

        public BranchBuilder branchAddressNotes(String branchAddressNotes) {
            mBranchAddressNotes = branchAddressNotes;
            return this;
        }

        public BranchBuilder branchPhoneNum(String branchPhoneNum) {
            mBranchPhoneNum = branchPhoneNum;
            return this;
        }

        public BranchBuilder branchLongitude(String branchLongitude) {
            mBranchLongitude = branchLongitude;
            return this;
        }

        public BranchBuilder branchLatitude(String branchLatitude) {
            mBranchLatitude = branchLatitude;
            return this;
        }

        public BranchBuilder branchRate(String branchRate) {
            mBranchRate = branchRate;
            return this;
        }

        public BranchBuilder appointments(String appointments) {
            mAppointments = appointments;
            return this;
        }

        public BranchBuilder branchLanguage(Integer branchLanguage) {
            mBranchLanguage = branchLanguage;
            return this;
        }
    }*/
}
