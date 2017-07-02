package com.example.ecss.medicalmapper.userInterface.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.Pharmacy;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sheri on 6/17/2017.
 */
public class SavedPharmaciesAdapter extends RecyclerView.Adapter<SavedPharmaciesAdapter.MyViewHolder> {

    private List<Pharmacy> mSavedPharmacies;
    private OnItemClickListener mOnItemClickListener;


    public SavedPharmaciesAdapter() {

    }

    public void updateAdapter(List<Pharmacy> places) {
        this.mSavedPharmacies = places;
        notifyDataSetChanged();
    }


    @Override
    public SavedPharmaciesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SavedPharmaciesAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_saved_pharmacy_item, parent, false),
                mOnItemClickListener
        );
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.setContent(mSavedPharmacies.get(position), position);
    }


    @Override
    public int getItemCount() {
        return mSavedPharmacies == null ? 0 : mSavedPharmacies.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.pharmacy_name_card)
        TextView pharmacyName = null;

        @BindView(R.id.delivery_card)
        TextView pharmacyDelivery = null;
        @BindView(R.id.pharmacy_address_card)
        TextView pharmacyAddress = null;

        private Pharmacy mPharmacy;
        private OnItemClickListener mOnItemClickListener;
        private int mPosition;

        MyViewHolder(View convertView, OnItemClickListener OnItemClickListener) {
            super(convertView);
            mOnItemClickListener = OnItemClickListener;
            ButterKnife.bind(this, convertView);

        }

        void setContent(Pharmacy pharmacy, int position) {
            mPharmacy = pharmacy;
            mPosition = position;
            pharmacyName.setText(pharmacy.getPharmacyName());
            if (pharmacy.getPharmacyDelivery() == true) {
                pharmacyDelivery.setText(pharmacyDelivery.getContext().getString(R.string.delivery));
            }
            pharmacyAddress.setText(pharmacy.getBranches().get(0).getBranchStreetName() + "," + pharmacy.getBranches().get(0).getBranchAddressNotes());
        }


        @OnClick({
                R.id.pharmacy_card_view
        })
        void onClick(View view) {
            Log.i("Method called: ", "onClick");
            Log.d("Method parameters: ", view.toString());
            if (mOnItemClickListener == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.pharmacy_card_view:
                    mOnItemClickListener.onLabClick(view, mPosition, mPharmacy);
                    break;
            }
        }

    }

    public interface OnItemClickListener {
        void onLabClick(View view, int position, Pharmacy savedPlace);
    }
}