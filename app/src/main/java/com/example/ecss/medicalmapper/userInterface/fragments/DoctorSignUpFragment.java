package com.example.ecss.medicalmapper.userInterface.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_certifications;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_degree;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_finish;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_overview;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_photo;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_specialization;
import static com.example.ecss.medicalmapper.R.id.doctor_signup_upload_photo;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getCroppedBitmap;
import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.savePremiumUserToSharedPreferences;


public class DoctorSignUpFragment extends Fragment {

    private static final int RESULT_LOAD_IMAGE = 1;
    private String mPhotoUri = "";

    @BindView(doctor_signup_degree)
    TextView mDegreeTextView;

    @BindView(doctor_signup_specialization)
    TextView mSpecializationTextView;

    @BindView(doctor_signup_finish)
    Button mFinishButton;

    @BindView(doctor_signup_overview)
    EditText mOverviewEditText;

    @BindView(doctor_signup_upload_photo)
    TextView mUploadPhotoTextView;

    @BindView(doctor_signup_photo)
    ImageView mPhotoImageView;

    @BindView(doctor_signup_certifications)
    EditText mCertificationsEditText;

    public DoctorSignUpFragment() {
    }

    public static DoctorSignUpFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        DoctorSignUpFragment fragment = new DoctorSignUpFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_doctor_sign_up, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }


    @OnClick({doctor_signup_degree, doctor_signup_specialization, R.id.doctor_signup_finish, R.id.doctor_signup_upload_photo})
    public void onClick(View v) {

        final String[] stringArray;
        switch (v.getId()) {
            case doctor_signup_degree:

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.title_degrees));

                stringArray = getResources().getStringArray(R.array.doctor_degrees_array);
                builder.setItems(stringArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDegreeTextView.setText(stringArray[which]);
                    }
                }).create().show();

                break;

            case doctor_signup_specialization:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.title_specializations));

                stringArray = getResources().getStringArray(R.array.doctor_specializations_array);
                builder.setItems(stringArray,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mSpecializationTextView.setText(stringArray[which]);
                            }
                        }).create().show();

                break;

            case doctor_signup_finish:

                if (validate()) {
                    if (checkInternetConnection()) {

                        final User user = getUserFromSharedPreferences(getActivity());

                        if (user.getUserId() != -1) {

                            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                            final Doctor request = new Doctor(user.getUserId(), user.getUserName(), mSpecializationTextView.getText().toString(), mDegreeTextView.getText().toString(), mOverviewEditText.getText().toString(), mPhotoUri, mCertificationsEditText.getText().toString());
                            Call<ApiCallStatus> call = apiService.DoctorSignUp(request);
                            call.enqueue(new Callback<ApiCallStatus>() {
                                @Override
                                public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {
                                    ApiCallStatus status = null;

                                    if (response.body() != null) {
                                        status = response.body();
                                    }

                                    if (status != null && status.getIsSuccessful()) {
                                        savePremiumUserToSharedPreferences(request, getActivity());
                                        HomeScreenActivity.startActivity(getActivity());
                                    } else {
                                        if (status != null && status.getErrorStatus() != null)
                                            Toast.makeText(getActivity(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ApiCallStatus> call, Throwable t) {
                                    //Toast.makeText(getActivity(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                                }
                            });

                        } else {
                            Toast.makeText(getActivity(), getString(R.string.sign_in_problem_message), Toast.LENGTH_LONG).show();
                        }
                    }
                }

                break;
            case doctor_signup_upload_photo:

                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {

            Uri selectedImage = data.getData();
            mPhotoUri = selectedImage.toString();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (bitmap != null) {
                Bitmap croppedBitmap = getCroppedBitmap(bitmap);
                if (croppedBitmap != null) {
                    mPhotoImageView.setImageBitmap(croppedBitmap);
                }
            }
        }
    }

    public boolean validate() {
        boolean valid = true;

        String specialization = null;
        if (mSpecializationTextView != null && mSpecializationTextView.getText() != null) {
            specialization = mSpecializationTextView.getText().toString();

            if (specialization.isEmpty()) {
                mSpecializationTextView.setError(getString(R.string.error_validate_specialization));
                valid = false;
            } else {
                mSpecializationTextView.setError(null);
            }
        }

        String degree = null;
        if (mDegreeTextView != null && mDegreeTextView.getText() != null) {
            degree = mDegreeTextView.getText().toString();

            if (degree.isEmpty()) {
                mDegreeTextView.setError(getString(R.string.error_validate_degree));
                valid = false;
            } else {
                mDegreeTextView.setError(null);
            }
        }


        String description = null;
        if (mOverviewEditText != null && mOverviewEditText.getText() != null) {
            description = mOverviewEditText.getText().toString();

            if (description.isEmpty()) {
                mOverviewEditText.setError(getString(R.string.error_validate_description));
                valid = false;
            } else {
                mOverviewEditText.setError(null);
            }
        }

        String certifications = null;
        if (mCertificationsEditText != null && mCertificationsEditText.getText() != null) {
            certifications = mCertificationsEditText.getText().toString();

            if (certifications.isEmpty()) {
                mCertificationsEditText.setError(getString(R.string.error_validate_certifications));
                valid = false;
            } else {
                mCertificationsEditText.setError(null);
            }
        }

        return valid;
    }
}
