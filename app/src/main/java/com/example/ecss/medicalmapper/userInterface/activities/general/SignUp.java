package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.ecss.medicalmapper.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, SignUp.class);
        return intent;
    }
}
