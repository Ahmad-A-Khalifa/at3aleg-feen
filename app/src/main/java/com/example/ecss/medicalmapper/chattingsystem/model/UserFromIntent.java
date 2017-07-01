package com.example.ecss.medicalmapper.chattingsystem.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserFromIntent /*extends RealmObject*/ implements Parcelable {

    public static final Creator<UserFromIntent> CREATOR = new Creator<UserFromIntent>() {
        @Override
        public UserFromIntent createFromParcel(Parcel in) {
            return new UserFromIntent(in);
        }

        @Override
        public UserFromIntent[] newArray(int size) {
            return new UserFromIntent[size];
        }
    };

    private Integer mUserId;

    private String mUserName;

    private String mUserEmail;

    private String mUserPassword;

    private Integer mUserType;

    private Integer mUserSavedPlace;


    public UserFromIntent(Parcel in) {
        this.mUserId = in.readInt();
        this.mUserName = in.readString();
        this.mUserEmail = in.readString();
        this.mUserPassword = in.readString();
        this.mUserType = in.readInt();
        this.mUserSavedPlace = in.readInt();
    }

    public UserFromIntent() {

    }

    public UserFromIntent(Integer userId, String userName, String userEmail, String userPassword, Integer userType, Integer userSavedPlace) {
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
