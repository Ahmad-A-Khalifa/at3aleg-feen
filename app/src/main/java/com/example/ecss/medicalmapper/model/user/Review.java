package com.example.ecss.medicalmapper.model.user;


public class Review {
    private String mReviewer;
    private String mReviewDescription;

    public Review(String reviewer, String reviewDescription) {
        this.mReviewer = reviewer;
        this.mReviewDescription = reviewDescription;
    }

    public String getReviewer() {
        return mReviewer;
    }

    public void setReviewer(String reviewer) {
        this.mReviewer = reviewer;
    }

    public String getReviewDescription() {
        return mReviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.mReviewDescription = reviewDescription;
    }
}
