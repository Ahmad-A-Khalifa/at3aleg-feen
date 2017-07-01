package com.example.ecss.medicalmapper.network.advancedSearchApiCall;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by E C S on 22/06/2017.
 */

public class AdvancedSearchPlaces {

    @SerializedName("branchs")
    private List<AdvancedSearchBranch> mBranches;

    public List<AdvancedSearchBranch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<AdvancedSearchBranch> mBranches) {
        this.mBranches = mBranches;
    }
}
