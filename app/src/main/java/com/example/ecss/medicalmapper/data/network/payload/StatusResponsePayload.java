package com.example.ecss.medicalmapper.data.network.payload;

import com.google.gson.annotations.SerializedName;

/**
 * Created by khalifa on 23/06/17.
 */

public class StatusResponsePayload {

    @SerializedName("status")
    private Status mStatus;

    public Status getStatus() {
        return mStatus;
    }

    public class Status {
        @SerializedName("is_successful")
        private boolean mIsSuccessful;

        @SerializedName("error_status")
        private String mErrorMessage;

        public boolean isSuccessful() {
            return mIsSuccessful;
        }

        public String getErrorMessage() {
            return mErrorMessage;
        }
    }
}
