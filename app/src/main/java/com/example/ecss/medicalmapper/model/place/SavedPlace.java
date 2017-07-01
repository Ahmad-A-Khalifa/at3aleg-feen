package com.example.ecss.medicalmapper.model.place;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class SavedPlace /*extends RealmObject*/ implements Parcelable {

    public static final Creator<SavedPlace> CREATOR = new Creator<SavedPlace>() {
        @Override
        public SavedPlace createFromParcel(Parcel source) {
            return new SavedPlace(source);
        }

        @Override
        public SavedPlace[] newArray(int size) {
            return new SavedPlace[size];
        }
    };
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("branch_id")
    private String mBranchId;
    @SerializedName("branch_type")
    private String mBranchType;

    public SavedPlace(Parcel in) {
        this.mUserId = in.readString();
        this.mBranchId = in.readString();
        this.mBranchType = in.readString();
    }

    public String getUserId() {
        return mUserId;
    }

    public void setUserId(String UserId) {
        this.mUserId = UserId;
    }

    public String getBranchType() {
        return mBranchType;
    }

    public void setBranchType(String branchType) {
        mBranchType = branchType;
    }

    public String getBranchId() {
        return mBranchId;
    }

    public void setBranchId(String BranchId) {
        this.mBranchId = BranchId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mUserId);
        dest.writeString(this.mBranchId);
        dest.writeString(this.mBranchType);
    }
}
