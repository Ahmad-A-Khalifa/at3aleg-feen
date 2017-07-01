package com.example.ecss.medicalmapper.model.user;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Review implements Parcelable {

    @SerializedName("commentDate")
    private Date mReviewDate;
    @SerializedName("commentId")
    private Integer mReviewId;
    @SerializedName("userName")
    private String mReviewerUserName;
    @SerializedName("comment")
    private String mReviewDescription;
    @SerializedName("userEmail")
    private String mReviewEmail;

    public Review(String reviewer, String reviewDescription) {
        this.mReviewerUserName = reviewer;
        this.mReviewDescription = reviewDescription;
    }


    protected Review(Parcel in) {
        mReviewerUserName = in.readString();
        mReviewDescription = in.readString();
        mReviewEmail = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public Date getReviewDate() {
        return mReviewDate;
    }

    public Integer getReviewId() {
        return mReviewId;
    }

    public void setReviewId(Integer mReviewId) {
        this.mReviewId = mReviewId;
    }

    public String getReviewerUserName() {
        return mReviewerUserName;
    }

    public void setReviewerUserName(String mReviewerUserName) {
        this.mReviewerUserName = mReviewerUserName;
    }

    public String getReviewDescription() {
        return mReviewDescription;
    }

    public void setReviewDescription(String mReviewDescription) {
        this.mReviewDescription = mReviewDescription;
    }

    public String getReviewEmail() {
        return mReviewEmail;
    }

    public void setReviewEmail(String mReviewEmail) {
        this.mReviewEmail = mReviewEmail;
    }

    public void setReviewDate(Date mReviewDate) {
        this.mReviewDate = mReviewDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mReviewerUserName);
        dest.writeString(mReviewDescription);
        dest.writeString(mReviewEmail);
    }
}
