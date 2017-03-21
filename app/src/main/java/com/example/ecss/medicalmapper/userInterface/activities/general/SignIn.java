package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ecss.medicalmapper.R;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class SignIn extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Facebook
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_sign_in);
    }
}
