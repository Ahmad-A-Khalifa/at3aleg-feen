package com.example.ecss.medicalmapper.model.place;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class SavedPlace extends RealmObject {
    @SerializedName("user_id")
    private String mUserId;

    @SerializedName("branch_id")
    private String mBranchId;

    @SerializedName("branch_type")
    private String mBranchType;

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmBranchId() {
        return mBranchId;
    }

    public void setmBranchId(String mBranchId) {
        this.mBranchId = mBranchId;
    }
}
