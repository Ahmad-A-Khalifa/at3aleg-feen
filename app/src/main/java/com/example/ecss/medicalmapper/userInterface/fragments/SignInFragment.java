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
import com.example.ecss.medicalmapper.userInterface.activities.general.HomeScreen;
import com.example.ecss.medicalmapper.userInterface.activities.general.SignUp;
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

import static android.content.Context.MODE_PRIVATE;
import static com.example.ecss.medicalmapper.R.id.btn_facebook;
import static com.example.ecss.medicalmapper.R.id.btn_google;
import static com.example.ecss.medicalmapper.R.id.btn_login;
import static com.example.ecss.medicalmapper.R.id.link_signup;
import static com.facebook.FacebookSdk.getApplicationContext;

//import com.facebook.FacebookSdk;

public class SignInFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private static final String TAG = "SignIn";
    private static final int RC_SIGN_IN = 9001;
    private View mRootView;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;

    private EditText _emailText;
    private EditText _passwordText;

    private android.support.v7.widget.AppCompatButton _loginButton;
    private TextView _signupLink;
    private com.google.android.gms.common.SignInButton _googleButton;
    private com.facebook.login.widget.LoginButton _facebookButton;


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
        _facebookButton.setReadPermissions("email");

        // If using in a fragment
        _facebookButton.setFragment(this);

        // Callback registration
        _facebookButton.registerCallback(CallbackManager.Factory.create(), new FacebookCallback<LoginResult>() {
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

        _emailText = (EditText) mRootView.findViewById(R.id.input_email);
        _passwordText = (EditText) mRootView.findViewById(R.id.input_password);

        _loginButton = (android.support.v7.widget.AppCompatButton) mRootView.findViewById(btn_login);
        _signupLink = (TextView) mRootView.findViewById(link_signup);
        _googleButton = (com.google.android.gms.common.SignInButton) mRootView.findViewById(R.id.btn_google);
        _facebookButton = (com.facebook.login.widget.LoginButton) mRootView.findViewById(R.id.btn_facebook);

        _loginButton.setOnClickListener(this);
        _signupLink.setOnClickListener(this);
        _googleButton.setOnClickListener(this);
        _facebookButton.setOnClickListener(this);

        return mRootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case btn_login:

                if (validate()) {
                    String email = _emailText.getText().toString();
                    String password = _passwordText.getText().toString();
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("LoginUser", MODE_PRIVATE).edit();
                    editor.putString("Email", email);
                    editor.putString("password", password);
                    editor.commit();
                    startActivity(HomeScreen.getIntent(getApplicationContext()));
                }

                break;

            case link_signup:
                startActivity(SignUp.getIntent(getApplicationContext()));
                break;

            case btn_google:
                signInWithGoogle();
                startActivity(HomeScreen.getIntent(getApplicationContext()));
                break;

            case btn_facebook:
                startActivity(HomeScreen.getIntent(getApplicationContext()));
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
            mRootView.findViewById(R.id.btn_google).setVisibility(View.GONE);
        } else {
            mStatusTextView.setText(R.string.signed_out);

            mRootView.findViewById(R.id.btn_google).setVisibility(View.VISIBLE);
        }
    }

    //Google
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // validates Email and Password
    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("less than 4 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
