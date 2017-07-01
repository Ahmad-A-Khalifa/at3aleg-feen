package com.example.ecss.medicalmapper.userInterface.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.Hospital;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SavedHospitalsAdapter extends RecyclerView.Adapter<SavedHospitalsAdapter.MyViewHolder> {

    private List<Hospital> mSavedHospitals;
    private OnItemClickListener mOnItemClickListener;

    public SavedHospitalsAdapter() {

    }

    public void updateAdapter(List<Hospital> places) {
        this.mSavedHospitals = places;
        notifyDataSetChanged();
    }


    @Override
    public SavedHospitalsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SavedHospitalsAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_saved_hospital_item, parent, false),
                mOnItemClickListener
        );


    }

    @Override
    public void onBindViewHolder(SavedHospitalsAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.setContent(mSavedHospitals.get(i), i);
    }


    @Override
    public int getItemCount() {
        return mSavedHospitals == null ? 0 : mSavedHospitals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.hospital_name_card)
        TextView hospitalName = null;

        @BindView(R.id.hospital_specialization_card)
        TextView hospitalSpecialization = null;
        @BindView(R.id.hospital_gov_card)
        TextView hospitalGov = null;
        @BindView(R.id.hospital_emerg_card)
        TextView hospitalEmerg = null;
        @BindView(R.id.hospital_address_card)
        TextView hospitalAdderss = null;

        private Hospital mHospital;
        private OnItemClickListener mOnItemClickListener;
        private int mPosition;

        MyViewHolder(View convertView, OnItemClickListener OnItemClickListener) {
            super(convertView);
            mOnItemClickListener = OnItemClickListener;
            ButterKnife.bind(this, convertView);

        }

        void setContent(Hospital hospital, int position) {
            mHospital = hospital;
            mPosition = position;
            hospitalName.setText(hospital.getHospitalName());
            if (hospital.getIsGovernment() == true) {
                hospitalGov.setText("Governmental");
            } else {
                hospitalGov.setText("Private");
            }
            if (hospital.getHospitalEmergency() == true) {
                hospitalEmerg.setText("Has Emergency");
            } else {
                hospitalEmerg.setText("No Emergency");
            }
            hospitalAdderss.setText(hospital.getBranches().get(0).getBranchStreetName() + "," + hospital.getBranches().get(0).getBranchAddressNotes());

        }


        @OnClick({
                R.id.hospital_card_view
        })
        void onClick(View view) {
            Log.i("Method called: ", "onClick");
            Log.d("Method parameters: ", view.toString());
            if (mOnItemClickListener == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.hospital_card_view:
                    mOnItemClickListener.onHospitalClick(view, mPosition, mHospital);
                    break;
            }
        }
    }


    public interface OnItemClickListener {
        void onHospitalClick(View view, int position, Hospital savedPlace);
    }
}
