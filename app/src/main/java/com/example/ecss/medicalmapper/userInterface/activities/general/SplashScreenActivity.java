package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.ecss.medicalmapper.R;

public class SplashScreenActivity extends AppCompatActivity implements Runnable {
    private static final Integer SPLASH_SCREEN_DELAY = 1500;

    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, SplashScreenActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }*/
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.request_type_shared_preference), Context.MODE_PRIVATE).edit();
        editor.putInt(getString(R.string.request_type_shared_preference_number), 4);
        editor.commit();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(this, SPLASH_SCREEN_DELAY);
    }

    @Override
    public void run() {
        HomeScreenActivity.startActivity(this);
        finish();
    }
}
