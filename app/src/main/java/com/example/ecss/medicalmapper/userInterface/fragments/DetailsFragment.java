package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.models.Clinic;
import com.example.ecss.medicalmapper.models.Hospital;
import com.example.ecss.medicalmapper.models.Laboratory;
import com.example.ecss.medicalmapper.models.Pharmacy;
import com.example.ecss.medicalmapper.models.Review;
import com.example.ecss.medicalmapper.userInterface.adapters.ReviewsAdapter;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailsFragment extends Fragment {
    private ListView list_reviews;
    private ReviewsAdapter adapter;
    private View rootView;
    private ArrayList<Review> HardCodedReviews = new ArrayList<Review>();

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

        list_reviews = (ListView) rootView.findViewById(R.id.list_reviews);


        if (intent.getSerializableExtra("Place") instanceof Hospital) {

            h = (Hospital) intent.getSerializableExtra("Place");

            Log.e("Medical Place", h.Specialization + " " + h.Doctor);

            HardCodedReviews.add(new Review("Sherif", "EL mostshfa ndifa gdan"));
            HardCodedReviews.add(new Review("Hazem", "EL mostshfa was3a gdan"));
            HardCodedReviews.add(new Review("Khalifa", "Amn el mostshfa mo3mltoh we7sha"));
            HardCodedReviews.add(new Review("Samuel", "Smells very bad"));

            name.setText(h.Name);

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);
            specialization.setText(h.Specialization);

            address.setText(h.BuildingNumber + " " + h.Street + " " + h.AddressNotes);
            PhoneNumber.setText(h.PhoneNumber);

        } else if (intent.getSerializableExtra("Place") instanceof Pharmacy) {

            ph = (Pharmacy) intent.getSerializableExtra("Place");
            Log.e("Medical Place", ph.Name + " " + ph.PhoneNumber);

            HardCodedReviews.add(new Review("Mohamed", "3alatol za7ma"));
            HardCodedReviews.add(new Review("Mai", "always crowded"));
            HardCodedReviews.add(new Review("Mena", "Awesome"));
            HardCodedReviews.add(new Review("Abdelrahman", "The pharmacist is so rude"));

            name.setText(ph.Name);

            address.setText(ph.BuildingNumber + " " + ph.Street + " " + ph.AddressNotes);
            PhoneNumber.setText(ph.PhoneNumber);


        } else if (intent.getSerializableExtra("Place") instanceof Clinic) {

            clinic = (Clinic) intent.getSerializableExtra("Place");
            Log.e("Medical Place", clinic.Doctor + " " + clinic.Appointments + " " + clinic.ClosedDays);

            HardCodedReviews.add(new Review("Moneer", "El momardin mo3mlthom kwyesa gdan"));
            HardCodedReviews.add(new Review("Amr", "bgd 3eyada to7fa"));
            HardCodedReviews.add(new Review("Mohey", "Very Good"));
            HardCodedReviews.add(new Review("Ahmed", "The doctor is arrogant"));

            name.setText(clinic.Doctor);

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(clinic.Specialization);

            address.setText(clinic.BuildingNumber + " " + clinic.Street + " " + clinic.AddressNotes);
            PhoneNumber.setText(clinic.PhoneNumber);

            View app = rootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            appointments.setText(clinic.Appointments);

            View cd = rootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            ClosedDays.setText(clinic.ClosedDays);

        } else if (intent.getSerializableExtra("Place") instanceof Laboratory) {

            lab = (Laboratory) intent.getSerializableExtra("Place");
            Log.e("Medical Place", lab.Name + " " + lab.PhoneNumber);

            HardCodedReviews.add(new Review("Alaa", "disgusting"));
            HardCodedReviews.add(new Review("Sara", "Bad"));
            HardCodedReviews.add(new Review("Safaa", "el as3ar ghelyt 3an a5er mra yarit tera3o el nas shwya"));
            HardCodedReviews.add(new Review("Mariam", "estnit dori ktir ana fdelt wa2fa 3shan msh la2ya makan a3od fel a5er et5n2t m3 mowzft el est2bal"));

            name.setText(lab.Doctor);

            View spec = rootView.findViewById(R.id.linear_specialization);
            spec.setVisibility(View.VISIBLE);

            specialization.setText(lab.Specialization);

            address.setText(lab.BuildingNumber + " " + lab.Street + " " + lab.AddressNotes);
            PhoneNumber.setText(lab.PhoneNumber);

            View app = rootView.findViewById(R.id.linear_appointments);
            app.setVisibility(View.VISIBLE);

            appointments.setText(lab.Appointments);

            View cd = rootView.findViewById(R.id.linear_closed_days);
            cd.setVisibility(View.VISIBLE);

            ClosedDays.setText(lab.ClosedDays);

        }

        Button AddReview = (Button) rootView.findViewById(R.id.AddReview);

        AddReview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getReview();
            }
        });

        adapter = new ReviewsAdapter(getActivity(), HardCodedReviews);
        list_reviews.setAdapter(adapter);

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

        adapter = new ReviewsAdapter(getActivity(), HardCodedReviews);
        list_reviews.setAdapter(adapter);
    }
}
