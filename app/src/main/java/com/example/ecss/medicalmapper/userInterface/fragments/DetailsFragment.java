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

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {

    private ReviewsAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View rootView;
    private ArrayList<Review> HardCodedReviews = new ArrayList<Review>();
    private RecyclerView list_reviews;

    public DetailsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_details, container, false);

        TextView name = (TextView) rootView.findViewById(R.id.place_name);
        TextView specialization = (TextView) rootView.findViewById(R.id.place_specialization);
        TextView address = (TextView) rootView.findViewById(R.id.place_address);
        TextView PhoneNumber = (TextView) rootView.findViewById(R.id.place_phonenumber);
        TextView appointments = (TextView) rootView.findViewById(R.id.place_appointments);
        TextView ClosedDays = (TextView) rootView.findViewById(R.id.place_closed_days);


        //Rating Bar
        RatingBar mRatingBar = (RatingBar) rootView.findViewById(R.id.rating);
        Drawable drawable = mRatingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFFF00"), PorterDuff.Mode.SRC_ATOP);

        //Get Intent from HomeScreen
        Intent intent = getActivity().getIntent();

        Hospital h;
        Pharmacy ph;
        Clinic clinic;
        Laboratory lab;

        list_reviews = (RecyclerView) rootView.findViewById(R.id.list_reviews);
        adapter = new ReviewsAdapter(HardCodedReviews);
        mLayoutManager = new LinearLayoutManager(getActivity());
        list_reviews.setLayoutManager(mLayoutManager);
        list_reviews.setItemAnimator(new DefaultItemAnimator());
        list_reviews.setAdapter(adapter);

        if (intent.getParcelableExtra("Place") instanceof Hospital) {

            h = (Hospital) intent.getParcelableExtra("Place");

            Log.e("Medical Place", h.getmSpecialization() + " " + h.getmDoctor());

            HardCodedReviews.add(new Review("Sherif", "EL mostshfa ndifa gdan"));
            HardCodedReviews.add(new Review("Hazem", "EL mostshfa was3a gdan"));
            HardCodedReviews.add(new Review("Khalifa", "Amn el mostshfa mo3mltoh we7sha"));
            HardCodedReviews.add(new Review("Samuel", "Smells very bad"));

            name.setText(h.getmName());

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);
            specialization.setText(h.getmSpecialization());

            address.setText(h.getmBuildingNumber() + " " + h.getmStreet() + " " + h.getmAddressNotes());
            PhoneNumber.setText(h.getmPhoneNumber());


        } else if (intent.getParcelableExtra("Place") instanceof Pharmacy) {

            ph = (Pharmacy) intent.getSerializableExtra("Place");
            Log.e("Medical Place", ph.getmName() + " " + ph.getmPhoneNumber());


            HardCodedReviews.add(new Review("Abdelrahman", "The pharmacist is so rude"));

            name.setText(ph.getmName());

            address.setText(ph.getmBuildingNumber() + " " + ph.getmStreet() + " " + ph.getmAddressNotes());
            PhoneNumber.setText(ph.getmPhoneNumber());


        } else if (intent.getParcelableExtra("Place") instanceof Clinic) {

            clinic = (Clinic) intent.getParcelableExtra("Place");
            Log.e("Medical Place", clinic.getmDoctor() + " " + clinic.getmAppointments() + " " + clinic.getmClosedDays());

            HardCodedReviews.add(new Review("Moneer", "El momardin mo3mlthom kwyesa gdan"));
            HardCodedReviews.add(new Review("Amr", "bgd 3eyada to7fa"));
            HardCodedReviews.add(new Review("Mohey", "Very Good"));
            HardCodedReviews.add(new Review("Ahmed", "The doctor is arrogant"));

            name.setText(clinic.getmDoctor());

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(clinic.getmSpecialization());

            address.setText(clinic.getmBuildingNumber() + " " + clinic.getmStreet() + " " + clinic.getmAddressNotes());
            PhoneNumber.setText(clinic.getmPhoneNumber());

            View app = rootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            appointments.setText(clinic.getmAppointments());

            View cd = rootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            ClosedDays.setText(clinic.getmClosedDays());


        } else if (intent.getParcelableExtra("Place") instanceof Laboratory) {

            lab = (Laboratory) intent.getParcelableExtra("Place");
            Log.e("Medical Place", lab.getmName() + " " + lab.getmPhoneNumber());

            HardCodedReviews.add(new Review("Alaa", "disgusting"));
            HardCodedReviews.add(new Review("Sara", "Bad"));
            HardCodedReviews.add(new Review("Safaa", "el as3ar ghelyt 3an a5er mra yarit tera3o el nas shwya"));
            HardCodedReviews.add(new Review("Mariam", "estnit dori ktir ana fdelt wa2fa 3shan msh la2ya makan a3od fel a5er et5n2t m3 mowzft el est2bal"));

            name.setText(lab.getmDoctor());

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(lab.getmSpecialization());

            address.setText(lab.getmBuildingNumber() + " " + lab.getmStreet() + " " + lab.getmAddressNotes());
            PhoneNumber.setText(lab.getmPhoneNumber());

            View app = rootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            appointments.setText(lab.getmAppointments());

            View cd = rootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            ClosedDays.setText(lab.getmClosedDays());


        }

        Button AddReview = (Button) rootView.findViewById(R.id.AddReview);

        AddReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getReview();
            }
        });


        adapter.notifyDataSetChanged();

        /*reviews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            }
        });*/

        return rootView;
    }

    public void getReview() {

        EditText review = (EditText) rootView.findViewById(R.id.review);

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
        HardCodedReviews.add(new Review(userName, text));

        review.setText("");

        adapter.notifyDataSetChanged();
    }
}
