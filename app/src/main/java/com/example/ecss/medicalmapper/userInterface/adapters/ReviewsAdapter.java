package com.example.ecss.medicalmapper.userInterface.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.models.Review;

import java.util.ArrayList;

/**
 * Created by ecss on 26/01/2017.
 */

public class ReviewsAdapter extends ArrayAdapter<Review> {
    private ArrayList<Review> Reviews;

    public ReviewsAdapter(final Context context, final ArrayList<Review> reviews) {
        super(context, 0, reviews);
        Reviews = reviews;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_review_item, parent, false);
        }

        final TextView reviewText = (TextView) convertView.findViewById(R.id.review_desc);
        final TextView reviewerText = (TextView) convertView.findViewById(R.id.reviewer);

        reviewText.setText(Reviews.get(position).getReviewDescription());
        reviewerText.setText(Reviews.get(position).getReviewer());

        return convertView;
    }
}
