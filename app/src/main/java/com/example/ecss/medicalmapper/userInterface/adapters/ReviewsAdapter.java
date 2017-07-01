package com.example.ecss.medicalmapper.userInterface.adapters;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.Review;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder> {

    private List<Review> mReviews;

    public ReviewsAdapter() {

    }

    public void updateAdapter(List<Review> Reviews) {
        this.mReviews = Reviews;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_review_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setContent(mReviews != null ? mReviews.get(position) : null);

    }

    @Override
    public int getItemCount() {
        //Log.e("Reviews in adapter ", String.valueOf(mReviews.size()));

        Log.i("Method called: ", "getItemCount");
        return mReviews != null ? mReviews.size() : 0;


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_desc)
        TextView reviewText = null;

        @BindView(R.id.reviewer)
        TextView reviewerText = null;

        private Review mReview;

        MyViewHolder(View convertView) {
            super(convertView);

            ButterKnife.bind(this, convertView);

        }

        void setContent(Review review) {
            mReview = review;
            reviewerText.setText(review.getReviewerUserName());
            reviewText.setText(review.getReviewDescription());
        }
    }
}
