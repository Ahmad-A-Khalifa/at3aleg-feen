package com.example.ecss.medicalmapper.userInterface.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.User.Review;

import java.util.ArrayList;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private ArrayList<Review> Reviews;


    public ReviewsAdapter(ArrayList<Review> Reviews) {
        this.Reviews = Reviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_review_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Review review = Reviews.get(position);
        holder.reviewerText.setText(review.getReviewer());
        holder.reviewText.setText(review.getReviewDescription());
    }

    @Override
    public int getItemCount() {
        return Reviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView reviewText = null;
        TextView reviewerText = null;

        public MyViewHolder(View convertView) {
            super(convertView);
            reviewText = (TextView) convertView.findViewById(R.id.review_desc);
            reviewerText = (TextView) convertView.findViewById(R.id.reviewer);
        }
    }
}