package com.example.ecss.medicalmapper.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sheri on 6/18/2017.
 */

public class ReviewRequest {

    @SerializedName("user_id")
    Integer mUserId;
    @SerializedName("branch_id")
    Integer mBranchId;
    @SerializedName("branch_type")
    String mBranchType;
    @SerializedName("comment")
    String mComment;
    @SerializedName("rate")
    Integer mRate;

    public ReviewRequest(Integer mUserId, Integer mBranchId, String mBranchType, String mComment, Integer mRate) {
        this.mUserId = mUserId;
        this.mBranchId = mBranchId;
        this.mBranchType = mBranchType;
        this.mComment = mComment;
        this.mRate = mRate;
    }
}
