package com.example.ecss.medicalmapper.models;

/**
 * Created by Sherif on 1/26/2017.
 */

public class Review {
    private String reviewer;

    public Review(String reviewer, String reviewDescription) {
        this.reviewer = reviewer;
        this.reviewDescription = reviewDescription;
    }

    public String getReviewer() {
        return reviewer;

    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    private String reviewDescription;
}
