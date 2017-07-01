package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.PlacesResponse;
import com.example.ecss.medicalmapper.model.Review;
import com.example.ecss.medicalmapper.model.ReviewRequest;
import com.example.ecss.medicalmapper.model.place.Clinic;
import com.example.ecss.medicalmapper.model.place.Hospital;
import com.example.ecss.medicalmapper.model.place.Laboratory;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.model.place.Pharmacy;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.CommentsResponse;
import com.example.ecss.medicalmapper.network.SavePlaceRequest;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.activities.general.ReportMedicalPlaceActivity;
import com.example.ecss.medicalmapper.userInterface.adapters.ReviewsAdapter;
import com.example.ecss.medicalmapper.utility.Utility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;

public class DetailsFragment extends Fragment {

    private static final String ARGUMENT_EXTRA_SAVED_PLACE = "Saved";
    private List<Review> mReviewsList;
    private static final String ARGUMENT_EXTRA_PLACE = "Place";

    @BindView(R.id.list_reviews)
    RecyclerView mListReviews;

    @BindView(R.id.AddReview)
    Button mAddReviewButton;

    @BindView(R.id.rating)
    RatingBar mRatingBar;

    @BindView(R.id.place_name)
    TextView mName;

    @BindView(R.id.place_specialization)
    TextView mSpecialization;

    @BindView(R.id.place_address)
    TextView mAddress;

    @BindView(R.id.place_phonenumber)
    TextView mPhoneNumber;

    @BindView(R.id.place_appointments)
    TextView mAppointments;

    @BindView(R.id.linear_specialization)
    View mSpecializationView;

    @BindView(R.id.linear_appointments)
    View mAppointmentView;

    @BindView(R.id.favorite_button)
    ImageButton mSavePlaceButton;

    @BindView(R.id.UnSaveButton)
    ImageButton mUnSavebutton;

    @BindView(R.id.report_outdated_medical_place)
    Button mReportOutdatedPlaceButton;

    Integer hospitalBranchId;
    String hospitalBranchType;
    Integer clinicBranchId;
    String clinicBranchType;
    String clinicEmail;
    String hospitalEmail;
    String pharmacyEmail;
    String labEmail;
    Integer pharmacyBranchId;
    String pharmacyBranchType;
    Integer labBranchId;
    String labBranchType;
    int isHospital;
    int isClinic;
    int isLab;
    int isPharmacy;
    Hospital hospital = null;
    Pharmacy pharmacy = null;
    Clinic clinic = null;
    Laboratory laboratory = null;

    private ReviewsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    public DetailsFragment() {

    }

    public static DetailsFragment newInstance(MedicalPlace place) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static DetailsFragment newInstanceSaved(MedicalPlaces places) {
        Bundle args = new Bundle();
        args.putParcelable(ARGUMENT_EXTRA_SAVED_PLACE, places);
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_details, container, false);

        ButterKnife.bind(this, rootView);

        if (getUserFromSharedPreferences(getActivity()).getUserId() != -1) {
            mReportOutdatedPlaceButton.setVisibility(View.VISIBLE);
        }

        Drawable drawable = mRatingBar.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFD700"), PorterDuff.Mode.SRC_ATOP);


        mAdapter = new ReviewsAdapter();
        mListReviews.setNestedScrollingEnabled(false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListReviews.setLayoutManager(mLayoutManager);
        mListReviews.setItemAnimator(new DefaultItemAnimator());
        mListReviews.setAdapter(mAdapter);


        if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Hospital) {

            hospital = (Hospital) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);

            Log.e("Medical Place", hospital.getHospitalName());


            mName.setText(hospital.getHospitalName());
            mSpecializationView.setVisibility(View.VISIBLE);
            mSpecialization.setText(getString(R.string.specialization_general));


            String buildingNumber = hospital.getBranches().get(0).getBranchBuildingNum();
            String streetName = hospital.getBranches().get(0).getBranchStreetName();
            String addressNotes = hospital.getBranches().get(0).getBranchAddressNotes();
            String phoneNum = hospital.getBranches().get(0).getBranchPhoneNum();

            mAddress.setText(buildingNumber + " " + streetName + " " + addressNotes);
            mPhoneNumber.setText(phoneNum);

            mRatingBar.setRating(Float.parseFloat(hospital.getBranches().get(0).getBranchRate()));

            getReviewsFromApi(hospital.getBranches().get(0).getBranchId());

            hospitalBranchId = hospital.getBranches().get(0).getBranchId();
            hospitalBranchType = hospital.getBranches().get(0).getBranchType();
            Log.e("branchID", hospitalBranchId + " " + hospitalBranchType);
            isHospital = 0;
            Log.e("isHospital on create", String.valueOf(isHospital));
            saveRequestTypeToSharedPreference(isHospital);


        } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Pharmacy) {

            pharmacy = (Pharmacy) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
            Log.e("Medical Place", pharmacy.getPharmacyName());


            mName.setText(pharmacy.getPharmacyName());

            String buildingNumber = pharmacy.getBranches().get(0).getBranchBuildingNum();
            String streetName = pharmacy.getBranches().get(0).getBranchStreetName();
            String addressNotes = pharmacy.getBranches().get(0).getBranchAddressNotes();
            String phoneNum = pharmacy.getBranches().get(0).getBranchPhoneNum();

            mAddress.setText(buildingNumber + " " + streetName + " " + addressNotes);
            mPhoneNumber.setText(phoneNum);

            mRatingBar.setRating(Float.parseFloat(pharmacy.getBranches().get(0).getBranchRate()));

            getReviewsFromApi(pharmacy.getBranches().get(0).getBranchId());


            pharmacyBranchId = pharmacy.getBranches().get(0).getBranchId();
            pharmacyBranchType = pharmacy.getBranches().get(0).getBranchType();
            isPharmacy = 2;
            Log.e("BranchID", pharmacyBranchId + "" + pharmacyBranchType);
            saveRequestTypeToSharedPreference(isPharmacy);

        } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Clinic) {

            clinic = (Clinic) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
            Log.e("Medical Place", clinic.getClinicName());


            mName.setText(clinic.getClinicName());

            mSpecializationView.setVisibility(View.VISIBLE);
            mSpecialization.setText(clinic.getClinicSpecialization());

            String buildingNumber = clinic.getBranches().get(0).getBranchBuildingNum();
            String streetName = clinic.getBranches().get(0).getBranchStreetName();
            String addressNotes = clinic.getBranches().get(0).getBranchAddressNotes();
            String phoneNum = clinic.getBranches().get(0).getBranchPhoneNum();

            mAddress.setText(buildingNumber + " " + streetName + " " + addressNotes);
            mPhoneNumber.setText(phoneNum);


            mAppointmentView.setVisibility(View.VISIBLE);

            mAppointments.setText(clinic.getBranches().get(0).getAppointments());
            mRatingBar.setRating(Float.parseFloat(clinic.getBranches().get(0).getBranchRate()));

            getReviewsFromApi(clinic.getBranches().get(0).getBranchId());


            clinicBranchId = clinic.getBranches().get(0).getBranchId();
            clinicBranchType = clinic.getBranches().get(0).getBranchType();
            Log.e("userID", String.valueOf(getUserFromSharedPreferences(getContext()).getUserId()));
            Log.e("BranchID", clinicBranchId + "  " + clinicBranchType);
            isClinic = 1;
            saveRequestTypeToSharedPreference(isClinic);


        } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Laboratory) {

            laboratory = (Laboratory) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
            Log.e("Medical Place", laboratory.getLabName());


            mName.setText(laboratory.getLabName());


            mSpecializationView.setVisibility(View.VISIBLE);

            mSpecialization.setText(laboratory.getLabSpecialization());

            String buildingNumber = laboratory.getBranches().get(0).getBranchBuildingNum();
            String streetName = laboratory.getBranches().get(0).getBranchStreetName();
            String addressNotes = laboratory.getBranches().get(0).getBranchAddressNotes();
            String phoneNum = laboratory.getBranches().get(0).getBranchPhoneNum();

            mAddress.setText(buildingNumber + " " + streetName + " " + addressNotes);
            mPhoneNumber.setText(phoneNum);


            mAppointmentView.setVisibility(View.VISIBLE);

            mAppointments.setText(laboratory.getBranches().get(0).getAppointments());

            mRatingBar.setRating(Float.parseFloat(laboratory.getBranches().get(0).getBranchRate()));

            getReviewsFromApi(laboratory.getBranches().get(0).getBranchId());

            labBranchId = laboratory.getBranches().get(0).getBranchId();
            labBranchType = laboratory.getBranches().get(0).getBranchType();
            Log.e("BranchID", labBranchId + "  " + labBranchType);
            isLab = 3;
            saveRequestTypeToSharedPreference(isLab);
        }

        if (getUserFromSharedPreferences(getContext()).getUserId() != -1) {
            mAddReviewButton.setVisibility(View.VISIBLE);
            //mChatButton.setVisibility(View.VISIBLE);
            if (Utility.checkInternetConnection()) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<PlacesResponse> call = apiService.GetSavedPlaces(getUserFromSharedPreferences(getContext()).getUserId());
                call.enqueue(new Callback<PlacesResponse>() {
                    @Override
                    public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {

                        MedicalPlaces places = null;
                        ApiCallStatus apiCallStatus = null;

                        if (response.body() != null) {
                            places = response.body().getMedicalPlaces();
                            apiCallStatus = response.body().getApiCallStatus();
                        }

                        if (apiCallStatus != null && places != null && apiCallStatus.getIsSuccessful()) {

                            if (getRequestTypeFromSharedPreference() == 0) {
                                for (Hospital hospitalLocal : places.getHospitals()) {
                                    if (hospitalLocal.getBranches().get(0) != null) {
                                        if (hospitalLocal.getBranches().get(0).getBranchId() == hospitalBranchId) {
                                            mSavePlaceButton.setVisibility(View.GONE);
                                            mUnSavebutton.setVisibility(View.VISIBLE);
                                            break;
                                        }

                                    }
                                }
                                mSavePlaceButton.setVisibility(View.VISIBLE);


                            } else if (getRequestTypeFromSharedPreference() == 2) {
                                for (Pharmacy pharmacyLocal : places.getPharmacies()) {
                                    if (pharmacyLocal.getBranches().get(0) != null) {
                                        if (pharmacyLocal.getBranches().get(0).getBranchId() == pharmacyBranchId) {
                                            mSavePlaceButton.setVisibility(View.GONE);
                                            mUnSavebutton.setVisibility(View.VISIBLE);
                                            Log.i("zzzzzz","unsave");
                                            Log.i("pharmacyBranchId : " + pharmacyBranchId, "=" + pharmacyLocal.getBranches().get(0).getBranchId());
                                            break;
                                        }

                                    }
                                }
                                mSavePlaceButton.setVisibility(View.VISIBLE);

                            } else if (getRequestTypeFromSharedPreference() == 3) {
                                for (Laboratory labLocal : places.getLaboratories()) {
                                    if (labLocal.getBranches().get(0) != null) {
                                        if (labLocal.getBranches().get(0).getBranchId() == labBranchId) {
                                            mSavePlaceButton.setVisibility(View.GONE);
                                            mUnSavebutton.setVisibility(View.VISIBLE);
                                            break;
                                        }
                                    }
                                }
                                mSavePlaceButton.setVisibility(View.VISIBLE);

                            } else if (getRequestTypeFromSharedPreference() == 1) {
                                for (Clinic clinicLocal : places.getClinics()) {
                                    if (clinicLocal.getBranches().get(0) != null) {
                                        if (clinicLocal.getBranches().get(0).getBranchId() == clinicBranchId) {
                                            mSavePlaceButton.setVisibility(View.GONE);
                                            Log.e("yala ba2a :", "visible");
                                            mUnSavebutton.setVisibility(View.VISIBLE);
                                            break;
                                        }
                                    }
                                }
                            }
                            mSavePlaceButton.setVisibility(View.VISIBLE);
                        }

                        else {
                            mSavePlaceButton.setVisibility(View.VISIBLE);

                        }

                    }

                    @Override
                    public void onFailure(Call<PlacesResponse> call, Throwable t) {
                    }
                });

            }

        }


        return rootView;
    }


    private Review getTmpReview() {
        return new Review("", "");
    }

    @OnClick({R.id.AddReview, R.id.favorite_button, R.id.UnSaveButton, R.id.report_outdated_medical_place}
    )
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.AddReview:
                if (getRequestTypeFromSharedPreference() == 0) {
                    showChangeLangDialog(getUserFromSharedPreferences(getContext()).getUserId(), hospitalBranchId, hospitalBranchType);
                } else if (getRequestTypeFromSharedPreference() == 2) {
                    showChangeLangDialog(getUserFromSharedPreferences(getContext()).getUserId(), pharmacyBranchId, pharmacyBranchType);

                } else if (getRequestTypeFromSharedPreference() == 3) {
                    showChangeLangDialog(getUserFromSharedPreferences(getContext()).getUserId(), labBranchId, labBranchType);

                } else if (getRequestTypeFromSharedPreference() == 1) {
                    showChangeLangDialog(getUserFromSharedPreferences(getContext()).getUserId(), clinicBranchId, clinicBranchType);

                }
                break;
            case R.id.favorite_button:

                if (getRequestTypeFromSharedPreference() == 0) {
                    Log.e("isHospital fav", String.valueOf(isHospital));
                    savePlaceToApi(getUserFromSharedPreferences(getContext()).getUserId(), hospitalBranchId, hospitalBranchType);
                } else if (getRequestTypeFromSharedPreference() == 2) {
                    savePlaceToApi(getUserFromSharedPreferences(getContext()).getUserId(), pharmacyBranchId, pharmacyBranchType);

                } else if (getRequestTypeFromSharedPreference() == 3) {
                    savePlaceToApi(getUserFromSharedPreferences(getContext()).getUserId(), labBranchId, labBranchType);

                } else if (getRequestTypeFromSharedPreference() == 1) {
                    savePlaceToApi(getUserFromSharedPreferences(getContext()).getUserId(), clinicBranchId, clinicBranchType);
                }
                break;
            case R.id.UnSaveButton:

                if (getRequestTypeFromSharedPreference() == 0) {
                    removePlaceFromApi(getUserFromSharedPreferences(getContext()).getUserId(), hospitalBranchId, hospitalBranchType);
                } else if (getRequestTypeFromSharedPreference() == 2) {
                    removePlaceFromApi(getUserFromSharedPreferences(getContext()).getUserId(), pharmacyBranchId, pharmacyBranchType);

                } else if (getRequestTypeFromSharedPreference() == 3) {
                    removePlaceFromApi(getUserFromSharedPreferences(getContext()).getUserId(), labBranchId, labBranchType);

                } else if (getRequestTypeFromSharedPreference() == 1) {
                    removePlaceFromApi(getUserFromSharedPreferences(getContext()).getUserId(), clinicBranchId, clinicBranchType);

                }

                break;

            case R.id.report_outdated_medical_place:

                if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Hospital) {

                    Hospital hospital = (Hospital) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
                    User user = getUserFromSharedPreferences(getActivity());

                    Log.e("User ID", user.getUserId() + "");
                    Log.e("Hospital ID", hospital.getHospitalId() + "");
                    Log.e("Branch ID", hospital.getBranches().get(0).getBranchId() + "");

                    if (user.getUserId() != -1 && hospital.getBranches() != null && hospital.getBranches().get(0) != null) {
                        ReportMedicalPlaceActivity.startActivity(getActivity(), user.getUserId(), hospital.getHospitalId(), hospital.getBranches().get(0).getBranchId());
                        //ReportMedicalPlaceActivity.startActivity(getActivity(), 1000, 1000, 1000);
                    }
                } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Pharmacy) {

                    Pharmacy pharmacy = (Pharmacy) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
                    User user = getUserFromSharedPreferences(getActivity());
                    if (user.getUserId() != -1 && pharmacy.getBranches() != null && pharmacy.getBranches().get(0) != null) {
                        ReportMedicalPlaceActivity.startActivity(getActivity(), user.getUserId(), pharmacy.getPharmacyId(), pharmacy.getBranches().get(0).getBranchId());
                    }

                } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Clinic) {

                    Clinic clinic = (Clinic) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
                    User user = getUserFromSharedPreferences(getActivity());
                    if (user.getUserId() != -1 && clinic.getBranches() != null && clinic.getBranches().get(0) != null) {
                        ReportMedicalPlaceActivity.startActivity(getActivity(), user.getUserId(), clinic.getClinicId(), clinic.getBranches().get(0).getBranchId());
                    }

                } else if (getArguments() != null && getArguments().containsKey(ARGUMENT_EXTRA_PLACE) && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) != null && getArguments().getParcelable(ARGUMENT_EXTRA_PLACE) instanceof Laboratory) {

                    Laboratory laboratory = (Laboratory) getArguments().getParcelable(ARGUMENT_EXTRA_PLACE);
                    User user = getUserFromSharedPreferences(getActivity());
                    if (user.getUserId() != -1 && laboratory.getBranches() != null && laboratory.getBranches().get(0) != null) {
                        ReportMedicalPlaceActivity.startActivity(getActivity(), user.getUserId(), laboratory.getLabId(), laboratory.getBranches().get(0).getBranchId());
                    }
                }

                break;
        }
    }

    private void removePlaceFromApi(Integer userId, Integer branchId, String branchType) {
        if (Utility.checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ApiCallStatus> call = apiService.UnSavePlace(new SavePlaceRequest(getUserFromSharedPreferences(getContext()).getUserId(), branchId, branchType));
            call.enqueue(new Callback<ApiCallStatus>() {
                @Override
                public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {

                    ApiCallStatus apiCallStatus = null;

                    if (response.body() != null) {
                        apiCallStatus = response.body();
                    }

                    if (apiCallStatus != null && apiCallStatus.getIsSuccessful()) {
                        Toast.makeText(getContext(), getActivity().getResources().getString(R.string.placeSaved), Toast.LENGTH_LONG).show();


                    } else {
                        if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                            Toast.makeText(getContext(), getActivity().getResources().getString(R.string.placeUnSaved), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiCallStatus> call, Throwable t) {

                }
            });


        } else {


        }
    }


    private void savePlaceToApi(Integer userId, Integer branchId, String branchType) {
        if (Utility.checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<ApiCallStatus> call = apiService.SavePlace(new SavePlaceRequest(getUserFromSharedPreferences(getContext()).getUserId(), branchId, branchType));
            call.enqueue(new Callback<ApiCallStatus>() {
                @Override
                public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {

                    ApiCallStatus apiCallStatus = null;

                    if (response.body() != null) {
                        apiCallStatus = response.body();
                    }

                    if (apiCallStatus != null && apiCallStatus.getIsSuccessful()) {
                        Toast.makeText(getContext(), apiCallStatus.getErrorStatus(), Toast.LENGTH_LONG).show();


                    } else {
                        if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                            Toast.makeText(getContext(), apiCallStatus.getErrorStatus(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<ApiCallStatus> call, Throwable t) {

                }
            });


        } else {


        }


    }

    private void addReview(Integer userId, Integer branchId, String branchType, String comment, Integer rate) {
        if (Utility.checkInternetConnection()) {
            if (getUserFromSharedPreferences(getContext()).getUserId() != -1) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<ApiCallStatus> call = apiService.AddReview(new ReviewRequest(userId, branchId, branchType, comment, rate));
                call.enqueue(new Callback<ApiCallStatus>() {
                    @Override
                    public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {

                        ApiCallStatus apiCallStatus = null;

                        if (response.body() != null) {
                            apiCallStatus = response.body();
                        }

                        if (apiCallStatus != null && apiCallStatus.getIsSuccessful()) {

                        } else {
                            if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                                Toast.makeText(getContext(), getActivity().getString(R.string.commentfail), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiCallStatus> call, Throwable t) {
                    }
                });

            } else {


            }


        }
    }

    private void getReviewsFromApi(Integer branchId) {
        if (Utility.checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
            Call<CommentsResponse> call = apiService.GetBranchComments(branchId);
            call.enqueue(new Callback<CommentsResponse>() {
                @Override
                public void onResponse(Call<CommentsResponse> call, Response<CommentsResponse> response) {

                    mReviewsList = null;
                    ApiCallStatus apiCallStatus = null;

                    if (response.body() != null) {
                        mReviewsList = response.body().getmCommentList();
                        apiCallStatus = response.body().getmStatus();
                    }

                    if (apiCallStatus != null && mReviewsList != null && apiCallStatus.getIsSuccessful()) {
                        mAdapter.updateAdapter(mReviewsList);

                    }
                    /*else {
                        if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                            Toast.makeText(getContext(), apiCallStatus.getErrorStatus(), Toast.LENGTH_LONG).show();
                    }*/
                }

                @Override
                public void onFailure(Call<CommentsResponse> call, Throwable t) {
                }
            });

        } else {

        }

    }


    // Review Dialog
    public void showChangeLangDialog(final Integer userId, final Integer branchId, final String branchType) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_review_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.review_custom_review_dialog);
        final RatingBar rt = (RatingBar) dialogView.findViewById(R.id.rating_custom_review_dialog);

        Drawable drawable = rt.getProgressDrawable();
        drawable.setColorFilter(Color.parseColor("#FFD700"), PorterDuff.Mode.SRC_ATOP);

        final User user = getUserFromSharedPreferences(getContext());
        dialogBuilder.setTitle(getString(R.string.review_by) + " " + user.getUserName());
        dialogBuilder.setPositiveButton(getString(R.string.Submit), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                if (validate(edt)) {
                    String text = edt.getText().toString();
                    int rate = (int) rt.getRating();
                    //edt.setText("");

                    if (user.getUserName() != null && text != null) {
                        if (mReviewsList == null) {
                            mReviewsList = new ArrayList<Review>();
                        }
                        mReviewsList.add(new Review(user.getUserName(), text));
                        Log.e("username", user.getUserName());
                        Log.e("text", text);
                        mAdapter.updateAdapter(mReviewsList);
                        addReview(userId, branchId, branchType, text, rate);
                        mAddReviewButton.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        dialogBuilder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Toast.makeText(getContext(), "failed", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    // validate reviews
    public boolean validate(EditText mReviewEditText) {

        boolean valid = true;

        String review = null;
        if (mReviewEditText != null && mReviewEditText.getText() != null) {
            review = mReviewEditText.getText().toString();
        }

        if (review.isEmpty()) {
            mReviewEditText.setError(getString(R.string.error_validate_review));
            valid = false;
        } else {
            mReviewEditText.setError(null);
        }

        return valid;
    }

    private void saveRequestTypeToSharedPreference(Integer requestType) {

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("details", Context.MODE_PRIVATE).edit();
        editor.putInt("detailsNum", requestType);
        editor.commit();
    }

    private int getRequestTypeFromSharedPreference() {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("details", Context.MODE_PRIVATE);
        return sharedPref.getInt("detailsNum", 4);
    }
}
