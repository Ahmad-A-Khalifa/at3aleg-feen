package com.example.ecss.medicalmapper.network.placesSearchApiCall;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlacesSearchBranch implements Serializable/*implements Parcelable*/ {

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
    private String mPhoneNumber;

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

    @SerializedName("clinic_id")
    private Integer mClinicId;

    @SerializedName("hospital_id")
    private Integer mHospitalId;

    @SerializedName("lab_id")
    private Integer mLabId;

    @SerializedName("pharmacy_id")
    private Integer mPharmacyId;

    @SerializedName("plac_name_AR")
    private String mPlaceNameAR;

    @SerializedName("place_name_EN")
    private String mPlaceNameEN;

    @SerializedName("specialization_AR")
    private String mSpecializationAR;

    @SerializedName("specialization_EN")
    private String mSpecializationEN;

    /*protected PlacesSearchBranch(Parcel in) {
        mPlaceNameAR = in.readString();
        mPlaceNameEN = in.readString();
        mSpecializationAR = in.readString();
        mSpecializationEN = in.readString();
        mBranchType = in.readString();
        mBranchStreetName = in.readString();
        mBranchBuildingNum = in.readString();
        mBranchFloorNum = in.readString();
        mBranchAppartmentNum = in.readString();
        mBranchAddressNotes = in.readString();
        mPhoneNumber = in.readString();
        mBranchLongitude = in.readString();
        mBranchLatitude = in.readString();
        mAppointments = in.readString();
        mBranchLanguage = in.readString();
    }

    public static final Creator<PlacesSearchBranch> CREATOR = new Creator<PlacesSearchBranch>() {
        @Override
        public PlacesSearchBranch createFromParcel(Parcel in) {
            return new PlacesSearchBranch(in);
        }

        @Override
        public PlacesSearchBranch[] newArray(int size) {
            return new PlacesSearchBranch[size];
        }
    };
*/
    public String getPlaceNameAR() {
        return mPlaceNameAR;
    }

    public String getPlaceNameEN() {
        return mPlaceNameEN;
    }

    public String getSpecializationAR() {
        return mSpecializationAR;
    }

    public String getSpecializationEN() {
        return mSpecializationEN;
    }

    public Integer getBranchId() {
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

    public String getPhoneNumber() {
        return mPhoneNumber;
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

    public Integer getClinicId() {
        return mClinicId;
    }

    public Integer getHospitalId() {
        return mHospitalId;
    }

    public Integer getLabId() {
        return mLabId;
    }

    public Integer getPharmacyId() {
        return mPharmacyId;
    }
    /*  @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mPlaceNameAR);
        parcel.writeString(mPlaceNameEN);
        parcel.writeString(mSpecializationAR);
        parcel.writeString(mSpecializationEN);
        parcel.writeString(mBranchType);
        parcel.writeString(mBranchStreetName);
        parcel.writeString(mBranchBuildingNum);
        parcel.writeString(mBranchFloorNum);
        parcel.writeString(mBranchAppartmentNum);
        parcel.writeString(mBranchAddressNotes);
        parcel.writeString(mPhoneNumber);
        parcel.writeString(mBranchLongitude);
        parcel.writeString(mBranchLatitude);
        parcel.writeString(mAppointments);
        parcel.writeString(mBranchLanguage);
    }*/

}
