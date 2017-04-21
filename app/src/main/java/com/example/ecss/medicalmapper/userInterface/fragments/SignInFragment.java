package com.example.ecss.medicalmapper.userInterface.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreenActivity;
import com.example.ecss.medicalmapper.userInterface.activities.general.SignUpActivity;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;
import static com.example.ecss.medicalmapper.R.id.btn_facebook;
import static com.example.ecss.medicalmapper.R.id.btn_google;
import static com.example.ecss.medicalmapper.R.id.btn_login;
import static com.example.ecss.medicalmapper.R.id.link_signup;

//import com.facebook.FacebookSdk;

public class SignInFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

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
    @BindView(R.id.btn_google)
    com.google.android.gms.common.SignInButton mGoogleButton;
    @BindView(R.id.btn_facebook)
    com.facebook.login.widget.LoginButton mFacebookButton;
    private View mRootView;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;


    public SignInFragment() {

    }

    @Override
    public void onViewCreated(View view, @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity() /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //Facebook
        mFacebookButton.setReadPermissions("email");

        // If using in a fragment
        mFacebookButton.setFragment(this);

        // Callback registration
        mFacebookButton.registerCallback(CallbackManager.Factory.create(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_sign_in, container, false);

        ButterKnife.bind(this, mRootView);


        // EmailEditText = (EditText) mRootView.findViewById(R.id.input_email);
        // mPasswordEditText = (EditText) mRootView.findViewById(R.id.input_password);

        //mLoginButton = (android.support.v7.widget.AppCompatButton) mRootView.findViewById(btn_login);
        //mSignupLinkTextView = (TextView) mRootView.findViewById(link_signup);
        //mGoogleButton = (com.google.android.gms.common.SignInButton) mRootView.findViewById(R.id.btn_google);
        //mFacebookButton = (com.facebook.login.widget.mLoginButton) mRootView.findViewById(R.id.btn_facebook);

        /*mLoginButton.setOnClickListener(this);
        mSignupLinkTextView.setOnClickListener(this);
        mGoogleButton.setOnClickListener(this);
        mFacebookButton.setOnClickListener(this);*/


        return mRootView;
    }

    @OnClick({R.id.btn_login, R.id.link_signup, R.id.btn_google, R.id.btn_facebook})
    public void onClick(View v) {

        switch (v.getId()) {
            case btn_login:

                if (validate()) {
                    String email = mEmailEditText.getText().toString();
                    String password = mPasswordEditText.getText().toString();
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("LoginUser", MODE_PRIVATE).edit();
                    editor.putString("Email", email);
                    editor.putString("password", password);
                    editor.commit();

                    startActivity(new Intent(getActivity(), HomeScreenActivity.class));
                }

                break;

            case link_signup:
                startActivity(new Intent(getActivity(), SignUpActivity.class));
                break;

            case btn_google:
                signInWithGoogle();
                startActivity(new Intent(getActivity(), HomeScreenActivity.class));
                break;

            case btn_facebook:
                startActivity(new Intent(getActivity(), HomeScreenActivity.class));
                break;
        }
    }

    //Google
    private void signInWithGoogle() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //Google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    //Google
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            updateUI(true);

        } else {

            // Signed out, show unauthenticated UI.
            updateUI(false);
        }
    }

    //Google
    private void updateUI(boolean signedIn) {
        if (signedIn) {

            //mRootView.findViewById(R.id.btn_google).setVisibility(View.GONE);
            mGoogleButton.setVisibility(View.GONE);

        } else {
            mStatusTextView.setText(R.string.signed_out);

            //mRootView.findViewById(R.id.btn_google).setVisibility(View.VISIBLE);
            mGoogleButton.setVisibility(View.VISIBLE);
        }
    }

    //Google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // validates Email and Password
    public boolean validate() {
        boolean valid = true;

        String email = mEmailEditText.getText().toString();
        String password = mPasswordEditText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailEditText.setError("enter a valid email address");
            valid = false;
        } else {
            mEmailEditText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            mPasswordEditText.setError("less than 4 alphanumeric characters");
            valid = false;
        } else {
            mPasswordEditText.setError(null);
        }
        return valid;
    }
}
