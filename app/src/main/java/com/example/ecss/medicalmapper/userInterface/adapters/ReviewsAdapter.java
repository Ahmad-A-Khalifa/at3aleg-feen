package com.example.ecss.medicalmapper.userInterface.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.Review;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private ArrayList<Review> mReviews;

    public ReviewsAdapter(ArrayList<Review> Reviews) {
        this.mReviews = Reviews;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_review_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Review review = mReviews.get(position);
        holder.reviewerText.setText(review.getReviewer());
        holder.reviewText.setText(review.getReviewDescription());
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_desc)
        TextView reviewText = null;

        @BindView(R.id.reviewer)
        TextView reviewerText = null;

        public MyViewHolder(View convertView) {
            super(convertView);

            ButterKnife.bind(this, convertView);

            //reviewText = (TextView) convertView.findViewById(R.id.review_desc);
            //reviewerText = (TextView) convertView.findViewById(R.id.reviewer);
        }
    }
}