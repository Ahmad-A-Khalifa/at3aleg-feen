package com.example.ecss.medicalmapper.userInterface.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.Clinic;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SavedClinicsAdapter extends RecyclerView.Adapter<SavedClinicsAdapter.MyViewHolder> {


    private List<Clinic> mSavedClinics;
    private OnItemClickListener mOnItemClickListener;

    public SavedClinicsAdapter() {

    }

    public void updateAdapter(List<Clinic> places) {
        this.mSavedClinics = places;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_saved_clinic_item, parent, false),
                mOnItemClickListener
        );

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setContent(mSavedClinics != null ? mSavedClinics.get(position) : null, position);
    }

    @Override
    public int getItemCount() {
        return mSavedClinics == null ? 0 : mSavedClinics.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.clinic_name_card)
        TextView clinicName = null;

        @BindView(R.id.clinic_specialization_card)
        TextView clinicSpealization = null;
        @BindView(R.id.clinic_appointment_card)
        TextView clinicAppointment = null;

        @BindView(R.id.clinic_address_card)
        TextView clinicAddress = null;

        private Clinic mClinic;
        private OnItemClickListener mOnItemClickListener;
        private int mPosition;


        MyViewHolder(View convertView, OnItemClickListener OnItemClickListener) {
            super(convertView);
            mOnItemClickListener = OnItemClickListener;
            ButterKnife.bind(this, convertView);

        }

        void setContent(Clinic clinic, int position) {
            mClinic = clinic;
            mPosition = position;
            clinicName.setText(mClinic.getClinicName());
            clinicSpealization.setText(mClinic.getClinicSpecialization());
            clinicAppointment.setText(mClinic.getBranches().get(0).getAppointments());
            clinicAddress.setText(mClinic.getBranches().get(0).getBranchStreetName() + "," + clinic.getBranches().get(0).getBranchAddressNotes());
        }


        @OnClick({
                R.id.clinic_card_view
        })
        void onClick(View view) {
            Log.i("Method called: ", "onClick");
            Log.d("Method parameters: ", view.toString());
            if (mOnItemClickListener == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.clinic_card_view:
                    mOnItemClickListener.onClinicClick(view, mPosition, mClinic);
                    break;
            }
        }
    }


    public interface OnItemClickListener {
        void onClinicClick(View view, int position, Clinic savedPlace);
    }
}

