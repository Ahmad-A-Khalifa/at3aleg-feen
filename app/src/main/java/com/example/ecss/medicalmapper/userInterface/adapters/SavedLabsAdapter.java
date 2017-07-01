package com.example.ecss.medicalmapper.userInterface.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.Laboratory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sheri on 6/17/2017.
 */

public class SavedLabsAdapter extends RecyclerView.Adapter<SavedLabsAdapter.MyViewHolder> {
    private List<Laboratory> mSavedLabs;
    private OnItemClickListener mOnItemClickListener;

    public SavedLabsAdapter() {

    }

    public void updateAdapter(List<Laboratory> places) {
        this.mSavedLabs = places;
        notifyDataSetChanged();
    }


    @Override
    public SavedLabsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SavedLabsAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_saved_lab_item, parent, false),
                mOnItemClickListener
        );

    }

    @Override
    public void onBindViewHolder(SavedLabsAdapter.MyViewHolder holder, int position) {
        holder.setContent(mSavedLabs.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mSavedLabs == null ? 0 : mSavedLabs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.lab_name_card)
        TextView labName = null;

        @BindView(R.id.lab_specialization_card)
        TextView labSpecialization = null;

        @BindView(R.id.lab_address_card)
        TextView labAddress = null;

        private Laboratory mLab;
        private OnItemClickListener mOnItemClickListener;
        private int mPosition;


        MyViewHolder(View convertView, OnItemClickListener OnItemClickListener) {
            super(convertView);
            mOnItemClickListener = OnItemClickListener;
            ButterKnife.bind(this, convertView);

        }

        void setContent(Laboratory lab, int position) {
            mLab = lab;
            mPosition = position;
            labName.setText(lab.getLabName());
            labSpecialization.setText(lab.getLabSpecialization());
            labAddress.setText(lab.getBranches().get(0).getBranchStreetName() + "," + lab.getBranches().get(0).getBranchAddressNotes());
        }


        @OnClick({
                R.id.lab_card_view
        })
        void onClick(View view) {
            Log.i("Method called: ", "onClick");
            Log.d("Method parameters: ", view.toString());
            if (mOnItemClickListener == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.lab_card_view:
                    mOnItemClickListener.onLabClick(view, mPosition, mLab);
                    break;
            }
        }
    }

    public interface OnItemClickListener {
        void onLabClick(View view, int position, Laboratory savedPlace);
    }
}
