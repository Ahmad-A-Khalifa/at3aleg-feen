package com.example.ecss.medicalmapper.network;

import com.example.ecss.medicalmapper.model.Review;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//import org.w3c.dom.Comment;

/**
 * Created by sheri on 6/17/2017.
 */

public class CommentsResponse {
    @SerializedName("status")
    ApiCallStatus mStatus;
    @SerializedName("respone")
    List<Review> mCommentList;

    public ApiCallStatus getmStatus() {
        return mStatus;
    }

    public List<Review> getmCommentList() {
        return mCommentList;
    }
}
