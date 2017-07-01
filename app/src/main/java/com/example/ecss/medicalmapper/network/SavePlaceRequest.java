package com.example.ecss.medicalmapper.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sheri on 6/18/2017.
 */

public class SavePlaceRequest {
    @SerializedName("user_id")
    Integer mUserId;
    @SerializedName("branch_id")
    Integer mBranchId;
    @SerializedName("branch_type")
    String mBranchType;

    public SavePlaceRequest(Integer mUserId, Integer mBranchId, String mBranchType) {
        this.mUserId = mUserId;
        this.mBranchId = mBranchId;
        this.mBranchType = mBranchType;
    }
}
