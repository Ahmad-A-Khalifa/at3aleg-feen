package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.Place.Clinic;
import com.example.ecss.medicalmapper.model.Place.Hospital;
import com.example.ecss.medicalmapper.model.Place.Laboratory;
import com.example.ecss.medicalmapper.model.Place.Pharmacy;
import com.example.ecss.medicalmapper.model.User.Review;
import com.example.ecss.medicalmapper.userInterface.adapters.ReviewsAdapter;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ecss.medicalmapper.R.id.list_reviews;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {

    private ReviewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View mRootView;
    private ArrayList<Review> mHardCodedReviews = new ArrayList<Review>();
    private RecyclerView mListReviews;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView name = (TextView) mRootView.findViewById(R.id.place_name);
        TextView specialization = (TextView) mRootView.findViewById(R.id.place_specialization);
        TextView address = (TextView) mRootView.findViewById(R.id.place_address);
        TextView PhoneNumber = (TextView) mRootView.findViewById(R.id.place_phonenumber);
        TextView appointments = (TextView) mRootView.findViewById(R.id.place_appointments);
        TextView ClosedDays = (TextView) mRootView.findViewById(R.id.place_closed_days);


        //Rating Bar
        RatingBar mRatingBar = (RatingBar) mRootView.findViewById(R.id.rating);
        Drawable drawable = mRatingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.SRC_ATOP);

        //Get Intent from HomeScreen
        Intent intent = getActivity().getIntent();

        Hospital h;
        Pharmacy ph;
        Clinic clinic;
        Laboratory lab;

        mListReviews = (RecyclerView) mRootView.findViewById(list_reviews);
        mAdapter = new ReviewsAdapter(mHardCodedReviews);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListReviews.setLayoutManager(mLayoutManager);
        mListReviews.setItemAnimator(new DefaultItemAnimator());
        mListReviews.setAdapter(mAdapter);

        if (intent.getParcelableExtra("Place") instanceof Hospital) {

            h = (Hospital) intent.getParcelableExtra("Place");

            Log.e("Medical Place", h.getmHospitalName());

            mHardCodedReviews.add(new Review("Sherif", "EL mostshfa ndifa gdan"));
            mHardCodedReviews.add(new Review("Hazem", "EL mostshfa was3a gdan"));
            mHardCodedReviews.add(new Review("Khalifa", "Amn el mostshfa mo3mltoh we7sha"));
            mHardCodedReviews.add(new Review("Samuel", "Smells very bad"));

            name.setText(h.getmHospitalName());

            View spec = mRootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);
            specialization.setText("General");


            String buildingNumber = h.getmBranches().get(h.getmHospitalId()).getmBranchBuildingNum();
            String streetName = h.getmBranches().get(h.getmHospitalId()).getmBranchStreetName();
            String addressNotes = h.getmBranches().get(h.getmHospitalId()).getmBranchAddressNotes();
            String phoneNum = h.getmBranches().get(h.getmHospitalId()).getmBranchPhoneNum();

            address.setText(buildingNumber + " " + streetName + " " + addressNotes);
            PhoneNumber.setText(phoneNum);


        } else if (intent.getParcelableExtra("Place") instanceof Pharmacy) {

            ph = (Pharmacy) intent.getSerializableExtra("Place");
            Log.e("Medical Place", ph.getmPharmacyName());


            mHardCodedReviews.add(new Review("Abdelrahman", "The pharmacist is so rude"));

            name.setText(ph.getmPharmacyName());

            String buildingNumber = ph.getmBranches().get(ph.getmPharmacyId()).getmBranchBuildingNum();
            String streetName = ph.getmBranches().get(ph.getmPharmacyId()).getmBranchStreetName();
            String addressNotes = ph.getmBranches().get(ph.getmPharmacyId()).getmBranchAddressNotes();
            String phoneNum = ph.getmBranches().get(ph.getmPharmacyId()).getmBranchPhoneNum();

            address.setText(buildingNumber + " " + streetName + " " + addressNotes);
            PhoneNumber.setText(phoneNum);


        } else if (intent.getParcelableExtra("Place") instanceof Clinic) {

            clinic = (Clinic) intent.getParcelableExtra("Place");
            Log.e("Medical Place", clinic.getmClinicName());

            mHardCodedReviews.add(new Review("Moneer", "El momardin mo3mlthom kwyesa gdan"));
            mHardCodedReviews.add(new Review("Amr", "bgd 3eyada to7fa"));
            mHardCodedReviews.add(new Review("Mohey", "Very Good"));
            mHardCodedReviews.add(new Review("Ahmed", "The doctor is arrogant"));

            name.setText(clinic.getmClinicName());

            View spec = mRootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(clinic.getmClinicSpecialization());

            String buildingNumber = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchBuildingNum();
            String streetName = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchStreetName();
            String addressNotes = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchAddressNotes();
            String phoneNum = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchPhoneNum();

            address.setText(buildingNumber + " " + streetName + " " + addressNotes);
            PhoneNumber.setText(phoneNum);

            View app = mRootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            String mAppointmentDay = clinic.getmBranches().get(clinic.getmClinicId()).getmAppointments().get(clinic.getmClinicId()).getmAppointmentDay();
            Integer mAppointmentFrom = clinic.getmBranches().get(clinic.getmClinicId()).getmAppointments().get(clinic.getmClinicId()).getmAppointmentFrom();
            Integer mAppointmentTo = clinic.getmBranches().get(clinic.getmClinicId()).getmAppointments().get(clinic.getmClinicId()).getmAppointmentTo();

            appointments.setText(mAppointmentDay + " " + mAppointmentFrom + " " + mAppointmentTo);

            View cd = mRootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            //ClosedDays.setText(clinic.getmClosedDays());


        } else if (intent.getParcelableExtra("Place") instanceof Laboratory) {

            lab = (Laboratory) intent.getParcelableExtra("Place");
            Log.e("Medical Place", lab.getmLabName());

            mHardCodedReviews.add(new Review("Alaa", "disgusting"));
            mHardCodedReviews.add(new Review("Sara", "Bad"));
            mHardCodedReviews.add(new Review("Safaa", "el as3ar ghelyt 3an a5er mra yarit tera3o el nas shwya"));
            mHardCodedReviews.add(new Review("Mariam", "estnit dori ktir ana fdelt wa2fa 3shan msh la2ya makan a3od fel a5er et5n2t m3 mowzft el est2bal"));

            name.setText(lab.getmLabName());

            View spec = mRootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(lab.getmLabSpecialization());

            String buildingNumber = lab.getmBranches().get(lab.getmLabId()).getmBranchBuildingNum();
            String streetName = lab.getmBranches().get(lab.getmLabId()).getmBranchStreetName();
            String addressNotes = lab.getmBranches().get(lab.getmLabId()).getmBranchAddressNotes();
            String phoneNum = lab.getmBranches().get(lab.getmLabId()).getmBranchPhoneNum();

            address.setText(buildingNumber + " " + streetName + " " + addressNotes);
            PhoneNumber.setText(phoneNum);

            View app = mRootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            String mAppointmentDay = lab.getmBranches().get(lab.getmLabId()).getmAppointments().get(lab.getmLabId()).getmAppointmentDay();
            Integer mAppointmentFrom = lab.getmBranches().get(lab.getmLabId()).getmAppointments().get(lab.getmLabId()).getmAppointmentFrom();
            Integer mAppointmentTo = lab.getmBranches().get(lab.getmLabId()).getmAppointments().get(lab.getmLabId()).getmAppointmentTo();

            appointments.setText(mAppointmentDay + " " + mAppointmentFrom + " " + mAppointmentTo);

            View cd = mRootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            //ClosedDays.setText(lab.getmClosedDays());

        }

        Button AddReview = (Button) mRootView.findViewById(R.id.AddReview);

        AddReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getReview();
            }
        });


        mAdapter.notifyDataSetChanged();

        /*reviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            }
        });*/

        return mRootView;
    }

    public void getReview() {

        EditText review = (EditText) mRootView.findViewById(R.id.review);

        String text = review.getText().toString();
        SharedPreferences prefs = getActivity().getSharedPreferences("LoginUser", MODE_PRIVATE);

        String email = prefs.getString("Email", "No name defined");
        String password = prefs.getString("password", "12345");
        String userName = "";
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') {
                break;
            }
            userName += email.charAt(i);
        }
        mHardCodedReviews.add(new Review(userName, text));

        review.setText("");

        mAdapter.notifyDataSetChanged();
    }
}
