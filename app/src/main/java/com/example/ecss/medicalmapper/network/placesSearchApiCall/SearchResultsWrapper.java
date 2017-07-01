package com.example.ecss.medicalmapper.network.placesSearchApiCall;

import java.io.Serializable;
import java.util.List;


public class SearchResultsWrapper implements Serializable/*implements Parcelable */ {

    /*public static final Creator<SearchResultsWrapper> CREATOR = new Creator<SearchResultsWrapper>() {
        @Override
        public SearchResultsWrapper createFromParcel(android.os.Parcel in) {
            return new SearchResultsWrapper(in);
        }

        @Override
        public SearchResultsWrapper[] newArray(int size) {
            return new SearchResultsWrapper[size];
        }
    };*/

    private List<PlacesSearchBranch> mBranches;

    public SearchResultsWrapper() {
    }

    public SearchResultsWrapper(List<PlacesSearchBranch> mBranches) {
        this.mBranches = mBranches;
    }

    /*public SearchResultsWrapper(Parcel in) {

        List<PlacesSearchBranch> myList = new ArrayList<>();
        in.readList(myList, getClass().getClassLoader());
        this.mBranches = myList;
    }
    */
    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.mBranches);
    }*/

    public List<PlacesSearchBranch> getBranches() {
        return mBranches;
    }

    public void setBranches(List<PlacesSearchBranch> mBranches) {
        this.mBranches = mBranches;
    }
}
