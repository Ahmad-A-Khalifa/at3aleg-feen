package com.example.ecss.medicalmapper.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.network.signUpApiCall.SignUpRequest;
import com.example.ecss.medicalmapper.network.signUpApiCall.SignUpResponse;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.utility.Utility.IsEmailCorrect;
import static com.example.ecss.medicalmapper.utility.Utility.IsPasswordCorrect;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.saveUserToSharedPreferences;

public class SignUpFragment extends Fragment {

    @BindView(R.id.sign_up_finish)
    Button mFinishButton;

    @BindView(R.id.sign_up_username)
    EditText mUsernameEditText;

    @BindView(R.id.sign_up_email)
    EditText mEmailEditText;

    @BindView(R.id.sign_up_password)
    EditText mPasswordEditText;

    @BindView(R.id.sign_up_progress_bar)
    ProgressBar mProgressBar;

    public SignUpFragment() {
    }

    public static SignUpFragment newInstance() {
        Bundle arguments = new Bundle();
        //arguments.putParcelable(ARGUMENT_EXTRA_PLACE, place);
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.sign_up_finish})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.sign_up_finish:

                if (validate()) {
                    if (checkInternetConnection()) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<SignUpResponse> call = apiService.SignUp(new SignUpRequest(mUsernameEditText.getText().toString(), mEmailEditText.getText().toString(), mPasswordEditText.getText().toString()));
                        call.enqueue(new Callback<SignUpResponse>() {
                            @Override
                            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {

                                User user = null;
                                ApiCallStatus status = null;

                                if (response.body() != null) {
                                    user = response.body().getUser();
                                    status = response.body().getApiCallStatus();
                                }

                                if (status != null && user != null && status.getIsSuccessful()) {

                                    saveUserToSharedPreferences(user, getActivity());
                                    HomeScreenActivity.startActivity(getActivity());

                                } else {
                                    if (status != null && status.getErrorStatus() != null)
                                        Toast.makeText(getActivity(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                                }
                                mProgressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                                mProgressBar.setVisibility(View.GONE);
                                //Toast.makeText(getActivity(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
                break;
        }
    }

    public boolean validate() {
        boolean valid = true;

        String username = null;
        if (mUsernameEditText != null && mUsernameEditText.getText() != null) {
            username = mUsernameEditText.getText().toString();

            if (username.isEmpty()) {
                mUsernameEditText.setError(getString(R.string.error_validate_username));
                valid = false;
            } else {
                mUsernameEditText.setError(null);
            }
        }


        String email = null;
        if (mEmailEditText != null && mEmailEditText.getText() != null) {
            email = mEmailEditText.getText().toString();

            if (!IsEmailCorrect(email)) {
                mEmailEditText.setError(getString(R.string.error_validate_email));
                valid = false;
            } else {
                mEmailEditText.setError(null);
            }
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
