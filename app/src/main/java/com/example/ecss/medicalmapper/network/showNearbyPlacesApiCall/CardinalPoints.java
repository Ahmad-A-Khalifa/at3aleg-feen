package com.example.ecss.medicalmapper.network.showNearbyPlacesApiCall;

import com.google.gson.annotations.SerializedName;


public class CardinalPoints {
    @SerializedName("north")
	private String mNorth;

    @SerializedName("south")
    private String mSouth;

    @SerializedName("east")
    private String mEast;

    @SerializedName("west")
    private String mWest;

    public CardinalPoints(String north, String south, String east, String west) {
        mNorth = north;
        mSouth = south;
        mEast = east;
        mWest = west;
    }

    public String getNorth() {
        return mNorth;
    }

    public void setNorth(String north) {
        mNorth = north;
    }

    public String getSouth() {
        return mSouth;
    }

    public void setSouth(String south) {
        mSouth = south;
    }

    public String getEast() {
        return mEast;
    }

    public void setEast(String east) {
        mEast = east;
    }

    public String getWest() {
        return mWest;
    }

    public void setWest(String west) {
        mWest = west;
    }
}
