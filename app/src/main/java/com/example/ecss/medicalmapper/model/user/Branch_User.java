package com.example.ecss.medicalmapper.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Branch_User /*extends RealmObject*/ implements Parcelable {

    public static final Creator<Branch_User> CREATOR = new Creator<Branch_User>() {
        @Override
        public Branch_User createFromParcel(Parcel source) {
            return new Branch_User(source);
        }

        @Override
        public Branch_User[] newArray(int size) {
            return new Branch_User[size];
        }
    };
    @SerializedName("branch_id")
    private String mBranchId;
    @SerializedName("user_id")
    private String mUserId;
    @SerializedName("branch_type")
    private String mBranchType;
    @SerializedName("comment")
    private String mComment;
    @SerializedName("rate")
    private Integer mRate;

    public Branch_User(Parcel in) {
        this.mBranchId = in.readString();
        this.mUserId = in.readString();
        this.mBranchType = in.readString();
        this.mComment = in.readString();
        this.mRate = in.readInt();
    }

    public String getBranchId() {
        return mBranchId;
    }

    public void setBranchId(String BranchId) {
        this.mBranchId = BranchId;
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

    public void setBranchType(String BranchType) {
        this.mBranchType = BranchType;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String Comment) {
        this.mComment = Comment;
    }

    public Integer getRate() {
        return mRate;
    }

    public void setRate(Integer Rate) {
        this.mRate = Rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.mBranchId);
        dest.writeString(this.mUserId);
        dest.writeString(this.mBranchType);
        dest.writeString(this.mComment);
        dest.writeInt(this.mRate);
    }
}
