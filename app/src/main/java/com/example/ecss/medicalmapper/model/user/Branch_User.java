package com.example.ecss.medicalmapper.model.user;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Branch_User extends RealmObject {

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

    public String getmBranchId() {
        return mBranchId;
    }

    public void setmBranchId(String mBranchId) {
        this.mBranchId = mBranchId;
    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmBranchType() {
        return mBranchType;
    }

    public void setmBranchType(String mBranchType) {
        this.mBranchType = mBranchType;
    }

    public String getmComment() {
        return mComment;
    }

    public void setmComment(String mComment) {
        this.mComment = mComment;
    }

    public Integer getmRate() {
        return mRate;
    }

    public void setmRate(Integer mRate) {
        this.mRate = mRate;
    }
}
