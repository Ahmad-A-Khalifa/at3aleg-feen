package com.example.ecss.medicalmapper.userInterface.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.GetPremiumDataResponse;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.network.signInApiCall.SignInRequest;
import com.example.ecss.medicalmapper.network.signInApiCall.SignInResponse;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;
import com.example.ecss.medicalmapper.userInterface.activities.general.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.R.id.btn_login;
import static com.example.ecss.medicalmapper.R.id.link_signup;
import static com.example.ecss.medicalmapper.utility.Utility.IsPasswordCorrect;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.savePremiumUserToSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.saveUserToSharedPreferences;

public class SignInFragment extends Fragment {

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    @BindView(R.id.input_email)
    EditText mEmailEditText;

    @BindView(R.id.input_password)
    EditText mPasswordEditText;

    @BindView(R.id.btn_login)
    android.support.v7.widget.AppCompatButton mLoginButton;

    @BindView(R.id.link_signup)
    TextView mSignupLinkTextView;

    @BindView(R.id.sign_in_progress_bar)
    ProgressBar mProgressBar;

    private ProgressDialog mProgressDialog;

    public SignInFragment() {

    }

    public static SignInFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        SignInFragment fragment = new SignInFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.btn_login, R.id.link_signup})
    public void onClick(View v) {

        switch (v.getId()) {
            case btn_login:

                if (validate()) {
                    if (checkInternetConnection()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<SignInResponse> call = apiService.SignIn(new SignInRequest(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));
                        call.enqueue(new Callback<SignInResponse>() {

                            @Override
                            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {

                                User user = null;
                                ApiCallStatus status = null;

                                if (response.body() != null) {
                                    user = response.body().getUser();
                                    status = response.body().getApiCallStatus();
                                }

                                if (status != null && user != null && status.getIsSuccessful()) {
                                    saveUserToSharedPreferences(user, getActivity());
                                    if (user.getUserType() == 2) {
                                        //Get Doctor data Api
                                        if (checkInternetConnection()) {
                                            ApiInterface apiServiceGetPremiumData = ApiClient.getClient().create(ApiInterface.class);
                                            Call<GetPremiumDataResponse> callGetPremiumData = apiServiceGetPremiumData.GetPremiumData(user.getUserId());
                                            callGetPremiumData.enqueue(new Callback<GetPremiumDataResponse>() {
                                                @Override
                                                public void onResponse(Call<GetPremiumDataResponse> call, Response<GetPremiumDataResponse> response) {

                                                    Doctor doctor = null;
                                                    ApiCallStatus status = null;

                                                    if (response.body() != null) {
                                                        doctor = response.body().getDoctor();
                                                        status = response.body().getApiCallStatus();
                                                    }

                                                    if (status != null && doctor != null && status.getIsSuccessful()) {
                                                        savePremiumUserToSharedPreferences(doctor, getActivity());
                                                        HomeScreenActivity.startActivity(getActivity());
                                                    } else {
                                                        if (status != null && status.getErrorStatus() != null)
                                                            Toast.makeText(getActivity(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<GetPremiumDataResponse> call, Throwable t) {
//Toast.makeText(getActivity(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }

                                    } else {
                                        HomeScreenActivity.startActivity(getActivity());
                                    }
                                } else {
                                    if (status != null && status.getErrorStatus() != null)
                                        Toast.makeText(getActivity(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                                }

                                mProgressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<SignInResponse> call, Throwable t) {
                                mProgressBar.setVisibility(View.GONE);
                                //Toast.makeText(getActivity(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                break;

            case link_signup:
                SignUpActivity.startActivity(getContext());
                break;

        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        hideProgressDialog();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    // validates Email and Password
    public boolean validate() {
        boolean valid = true;

        String email = null;
        if (mEmailEditText != null && mEmailEditText.getText() != null) {
            email = mEmailEditText.getText().toString();
/*
            if (!IsEmailCorrect(email)) {
                mEmailEditText.setError(getString(R.string.error_validate_email));
                valid = false;
            } else {
                mEmailEditText.setError(null);
            }*/
        }


        String password = null;
        if (mPasswordEditText != null && mPasswordEditText.getText() != null) {
            password = mPasswordEditText.getText().toString();

            if (!IsPasswordCorrect(password)) {
                mPasswordEditText.setError(getString(R.string.error_validate_password));
                valid = false;
            } else {
                mPasswordEditText.setError(null);
            }
        }

        return valid;
    }
}
