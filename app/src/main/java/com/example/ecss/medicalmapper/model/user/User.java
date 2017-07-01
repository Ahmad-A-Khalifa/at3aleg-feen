package com.example.ecss.medicalmapper.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User /*extends RealmObject*/ implements Parcelable {

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @SerializedName("user_id")
    private Integer mUserId;

    @SerializedName("user_name")
    private String mUserName;

    @SerializedName("user_email")
    private String mUserEmail;

    @SerializedName("user_password")
    private String mUserPassword;

    @SerializedName("user_type")
    private Integer mUserType;

    @SerializedName("user_saved_place")
    private Integer mUserSavedPlace;


    public User(Parcel in) {
        this.mUserId = in.readInt();
        this.mUserName = in.readString();
        this.mUserEmail = in.readString();
        this.mUserPassword = in.readString();
        this.mUserType = in.readInt();
        this.mUserSavedPlace = in.readInt();
    }

    public User() {

    }

    public User(Integer userId, String userName, String userEmail, String userPassword, Integer userType, Integer userSavedPlace) {
        mUserId = userId;
        mUserName = userName;
        mUserEmail = userEmail;
        mUserPassword = userPassword;
        mUserType = userType;
        mUserSavedPlace = userSavedPlace;
    }

    public void setUserId(Integer mUserId) {
        this.mUserId = mUserId;
    }

    public void setUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public void setUserEmail(String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }

    public void setUserPassword(String mUserPassword) {
        this.mUserPassword = mUserPassword;
    }

    public void setUserType(Integer mUserType) {
        this.mUserType = mUserType;
    }

    public void setUserSavedPlace(Integer mUserSavedPlace) {
        this.mUserSavedPlace = mUserSavedPlace;
    }

    public Integer getUserId() {
        return mUserId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public Integer getUserType() {
        return mUserType;
    }

    public Integer getUserSavedPlace() {
        return mUserSavedPlace;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mUserId);
        dest.writeString(this.mUserName);
        dest.writeString(this.mUserEmail);
        dest.writeString(this.mUserPassword);
        dest.writeInt(this.mUserType);
        dest.writeInt(this.mUserSavedPlace);
    }
}
