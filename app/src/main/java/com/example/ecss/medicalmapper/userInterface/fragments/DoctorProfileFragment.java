package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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
import static com.example.ecss.medicalmapper.R.id.doctor_profile_certifications;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_degree;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_doctor_name;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_overview;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_photo;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_save;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_specialization;
import static com.example.ecss.medicalmapper.R.id.doctor_profile_upload_photo;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getCroppedBitmap;
import static com.example.ecss.medicalmapper.utility.Utility.getPremiumUserFromSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.savePremiumUserToSharedPreferences;

public class DoctorProfileFragment extends Fragment {
    private static final int RESULT_LOAD_IMAGE = 1;

    @BindView(doctor_profile_doctor_name)
    TextView mDoctorNameTextView;

    @BindView(doctor_profile_degree)
    TextView mDegreeTextView;

    @BindView(doctor_profile_specialization)
    TextView mSpecializationTextView;

    @BindView(doctor_profile_overview)
    TextView mOverviewTextView;

    @BindView(doctor_profile_upload_photo)
    TextView mUploadPhotoTextView;

    @BindView(doctor_profile_photo)
    ImageView mPhotoImageView;

    @BindView(doctor_profile_certifications)
    TextView mCertificationsTextView;

    @BindView(doctor_profile_save)
    Button mSaveButton;

    private View mDialogView;
    private EditText mUpdateEditText;
    private AlertDialog mAlertDialog;
    private AlertDialog.Builder mDialogBuilder;
    private Doctor mRequest;

    public static DoctorProfileFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        DoctorProfileFragment fragment = new DoctorProfileFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    public DoctorProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_doctor_profile, container, false);

        ButterKnife.bind(this, rootView);

        mRequest = getPremiumUserFromSharedPreferences(getActivity());

        if (mRequest.getUserId() != -1) {
            mDoctorNameTextView.setText(mRequest.getDoctorName());
            mDegreeTextView.setText(mRequest.getDoctorDegree());
            mSpecializationTextView.setText(mRequest.getDoctorSpecialization());
            mOverviewTextView.setText(mRequest.getDoctorOverview());
            mCertificationsTextView.setText(mRequest.getDoctorCertifications());


            if (mRequest.getDoctorPhoto() != null && !mRequest.getDoctorPhoto().equals("-1") && !mRequest.getDoctorPhoto().isEmpty()) {

                Uri photoUri = Uri.parse(mRequest.getDoctorPhoto());

                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), photoUri);
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

        return rootView;
    }

    @OnClick({R.id.doctor_profile_doctor_name, R.id.doctor_profile_specialization, R.id.doctor_profile_degree, R.id.doctor_profile_overview, R.id.doctor_profile_certifications, R.id.doctor_profile_save, R.id.doctor_profile_upload_photo})
    public void onClick(View v) {
        final String[] stringArray;
        switch (v.getId()) {
            case doctor_profile_doctor_name:

                mDialogBuilder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();

                mDialogView = inflater.inflate(R.layout.custom_edittext_dialog, null);
                mDialogBuilder.setView(mDialogView);

                mUpdateEditText = ButterKnife.findById(mDialogView, R.id.custom_dialog_edittext);

                mDialogBuilder.setTitle(getString(R.string.doctor_name_update));

                mDialogBuilder.setPositiveButton(getString(R.string.Submit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (mUpdateEditText != null && mUpdateEditText.getText() != null) {
                            mDoctorNameTextView.setText(mUpdateEditText.getText().toString());
                            mRequest.setDoctorName(mUpdateEditText.getText().toString());
                            mSaveButton.setVisibility(View.VISIBLE);
                        }

                    }
                });

                mDialogBuilder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                mAlertDialog = mDialogBuilder.create();
                mAlertDialog.show();

                break;

            case doctor_profile_specialization:

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.title_specializations));
                stringArray = getResources().getStringArray(R.array.doctor_specializations_array);
                builder.setItems(stringArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSpecializationTextView.setText(stringArray[which]);
                        mRequest.setDoctorSpecialization(stringArray[which]);
                        mSaveButton.setVisibility(View.VISIBLE);
                    }
                }).create().show();


                break;

            case doctor_profile_degree:

                builder = new AlertDialog.Builder(getContext());
                builder.setTitle(getString(R.string.title_degrees));
                stringArray = getResources().getStringArray(R.array.doctor_degrees_array);
                builder.setItems(stringArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDegreeTextView.setText(stringArray[which]);
                        mRequest.setDoctorDegree(stringArray[which]);
                        mSaveButton.setVisibility(View.VISIBLE);
                    }
                }).create().show();


                break;

            case doctor_profile_overview:

                mDialogBuilder = new AlertDialog.Builder(getActivity());
                inflater = getActivity().getLayoutInflater();

                mDialogView = inflater.inflate(R.layout.custom_edittext_dialog, null);
                mDialogBuilder.setView(mDialogView);

                mUpdateEditText = ButterKnife.findById(mDialogView, R.id.custom_dialog_edittext);

                mDialogBuilder.setTitle(getString(R.string.overview_update));

                mDialogBuilder.setPositiveButton(getString(R.string.Submit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (mUpdateEditText != null && mUpdateEditText.getText() != null) {
                            mOverviewTextView.setText(mUpdateEditText.getText().toString());
                            mRequest.setDoctorOverview(mUpdateEditText.getText().toString());
                            mSaveButton.setVisibility(View.VISIBLE);
                        }
                    }
                });

                mDialogBuilder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                mAlertDialog = mDialogBuilder.create();
                mAlertDialog.show();

                break;

            case doctor_profile_certifications:

                mDialogBuilder = new AlertDialog.Builder(getActivity());
                inflater = getActivity().getLayoutInflater();

                mDialogView = inflater.inflate(R.layout.custom_edittext_dialog, null);
                mDialogBuilder.setView(mDialogView);

                mUpdateEditText = ButterKnife.findById(mDialogView, R.id.custom_dialog_edittext);

                mDialogBuilder.setTitle(getString(R.string.certifications_update));

                mDialogBuilder.setPositiveButton(getString(R.string.Submit), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        if (mUpdateEditText != null && mUpdateEditText.getText() != null) {
                            mCertificationsTextView.setText(mUpdateEditText.getText().toString());
                            mRequest.setDoctorCertifications(mUpdateEditText.getText().toString());
                            mSaveButton.setVisibility(View.VISIBLE);
                        }
                    }
                });

                mDialogBuilder.setNegativeButton(getString(R.string.Cancel), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                mAlertDialog = mDialogBuilder.create();
                mAlertDialog.show();

                break;

            case doctor_profile_save:

                if (checkInternetConnection()) {

                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<ApiCallStatus> call = apiService.DoctorEditProfile(mRequest);
                    call.enqueue(new Callback<ApiCallStatus>() {
                        @Override
                        public void onResponse(Call<ApiCallStatus> call, Response<ApiCallStatus> response) {
                            ApiCallStatus status = null;

                            if (response.body() != null) {
                                status = response.body();
                            }

                            if (status != null && status.getIsSuccessful()) {
                                savePremiumUserToSharedPreferences(mRequest, getActivity());
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
                }

                break;
            case doctor_profile_upload_photo:
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
            mRequest.setDoctorPhoto(selectedImage.toString());

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
                    mSaveButton.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}