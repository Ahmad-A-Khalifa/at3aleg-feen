package com.example.ecss.medicalmapper.utility;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utility {
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static final int MIN_PASSWORD_LENGTH = 4;

    public static boolean checkInternetConnection() {
        return ConnectivityReceiver.isConnected();
    }

    public static void showSnackbar(boolean isConnected, View view, Context context) {
        if (isConnected) {
            showSnackbarConnected(view, context);
        } else {
            showSnackbarDisconnected(view, context);
        }
    }

    private static void showSnackbarConnected(View view, Context context) {
        Snackbar snackbar = Snackbar.make(view, R.string.snackbar_message_connected, Snackbar.LENGTH_LONG);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        snackbar.show();
    }

    public static void showSnackbarDisconnected(View view, Context context) {
        Snackbar snackbar = Snackbar.make(view, R.string.snackbar_message_disconnected, Snackbar.LENGTH_INDEFINITE);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        snackbar.show();
    }

    public static boolean IsEmailCorrect(String email) {
        if (email.isEmpty()) {
            return false;
        } else {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);

            if (matcher.find()) {
                return true;
            } else return false;
        }
    }

    public static boolean IsPasswordCorrect(String password) {

        if (password.isEmpty()) {
            return false;
        } else {
            return password.length() >= MIN_PASSWORD_LENGTH;
        }
    }

    public static void saveUserToSharedPreferences(User user, Context context) {

        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.shared_pref_signed_in_user), Context.MODE_PRIVATE).edit();
        editor.putInt(context.getString(R.string.shared_pref_signed_in_user_id), user.getUserId());
        editor.putString(context.getString(R.string.shared_pref_signed_in_user_name), user.getUserName());
        editor.putString(context.getString(R.string.shared_pref_signed_in_user_email), user.getUserEmail());
        editor.putInt(context.getString(R.string.shared_pref_signed_in_user_type), user.getUserType());
        editor.putInt(context.getString(R.string.shared_pref_signed_in_user_saved_place), user.getUserSavedPlace());
        editor.commit();


        Log.e("UserSharedPreferences ", "" + user.getUserId());
        Log.e("UserSharedPreferences ", user.getUserName());
        Log.e("UserSharedPreferences ", user.getUserEmail());
        Log.e("UserSharedPreferences ", "" + user.getUserType());
        Log.e("UserSharedPreferences ", "" + user.getUserSavedPlace());
    }

    public static User getUserFromSharedPreferences(Context context) {
        User user = new User();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_pref_signed_in_user), Context.MODE_PRIVATE);

        user.setUserId(sharedPref.getInt(context.getString(R.string.shared_pref_signed_in_user_id), -1));
        user.setUserEmail(sharedPref.getString(context.getString(R.string.shared_pref_signed_in_user_email), "-1"));
        user.setUserName(sharedPref.getString(context.getString(R.string.shared_pref_signed_in_user_name), "-1"));
        user.setUserType(sharedPref.getInt(context.getString(R.string.shared_pref_signed_in_user_type), -1));
        user.setUserSavedPlace(sharedPref.getInt(context.getString(R.string.shared_pref_signed_in_user_saved_place), -1));

        Log.e("UserSharedPreferences ", "" + user.getUserId());
        Log.e("UserSharedPreferences ", user.getUserName());
        Log.e("UserSharedPreferences ", user.getUserEmail());
        Log.e("UserSharedPreferences ", "" + user.getUserType());
        Log.e("UserSharedPreferences ", "" + user.getUserSavedPlace());

        return user;
    }

    public static void savePremiumUserToSharedPreferences(Doctor doctor, Context context) {

        SharedPreferences.Editor editor = context.getSharedPreferences(context.getString(R.string.shared_pref_premium_user), Context.MODE_PRIVATE).edit();

        editor.putInt(context.getString(R.string.shared_pref_signed_in_user_id), doctor.getUserId());
        editor.putString(context.getString(R.string.shared_pref_premium_user_name), doctor.getDoctorName());
        editor.putString(context.getString(R.string.shared_pref_premium_user_specialization), doctor.getDoctorSpecialization());
        editor.putString(context.getString(R.string.shared_pref_premium_user_degree), doctor.getDoctorDegree());
        editor.putString(context.getString(R.string.shared_pref_premium_user_overview), doctor.getDoctorOverview());
        editor.putString(context.getString(R.string.shared_pref_premium_user_photo), doctor.getDoctorPhoto());
        editor.putString(context.getString(R.string.shared_pref_premium_user_certifications), doctor.getDoctorCertifications());

        editor.commit();

        Log.e("PremSharedPreferences ", "" + doctor.getUserId());
        Log.e("PremSharedPreferences ", doctor.getDoctorName());
        Log.e("PremSharedPreferences ", doctor.getDoctorSpecialization());
        Log.e("PremSharedPreferences ", doctor.getDoctorDegree());
        Log.e("PremSharedPreferences ", doctor.getDoctorOverview());
        Log.e("PremSharedPreferences ", doctor.getDoctorPhoto());
        Log.e("PremSharedPreferences ", doctor.getDoctorCertifications());
    }

    public static Doctor getPremiumUserFromSharedPreferences(Context context) {
        Doctor doctor = new Doctor();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.shared_pref_premium_user), Context.MODE_PRIVATE);

        doctor.setUserId(sharedPref.getInt(context.getString(R.string.shared_pref_signed_in_user_id), -1));
        doctor.setDoctorName(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_name), "-1"));
        doctor.setDoctorSpecialization(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_specialization), "-1"));
        doctor.setDoctorDegree(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_degree), "-1"));
        doctor.setDoctorOverview(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_overview), "-1"));
        doctor.setDoctorPhoto(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_photo), "-1"));
        doctor.setDoctorCertifications(sharedPref.getString(context.getString(R.string.shared_pref_premium_user_certifications), "-1"));

        Log.e("PremSharedPreferences ", "" + doctor.getUserId());
        Log.e("PremSharedPreferences ", doctor.getDoctorName());
        Log.e("PremSharedPreferences ", doctor.getDoctorSpecialization());
        Log.e("PremSharedPreferences ", doctor.getDoctorDegree());
        Log.e("PremSharedPreferences ", doctor.getDoctorOverview());
        Log.e("PremSharedPreferences ", doctor.getDoctorPhoto());
        Log.e("PremSharedPreferences ", doctor.getDoctorCertifications());

        return doctor;
    }

    public static Bitmap getCroppedBitmap(Bitmap scaleBitmapImage) {
        int targetWidth = 400;
        int targetHeight = 400;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }

    public static boolean openApp(Context context, String packageName, User user) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
       // i.putExtra("IntentObject", user);
        if (i == null) {
            return false;
//throw new PackageManager.NameNotFoundException();
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }
    public static Integer getLanguageFromSettings(Context context) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return Integer.parseInt(pref.getString(context.getString(R.string.settings_language_key), "0"));
    }

    public static Integer getLanguageFromSettings() {
        if (Locale.getDefault().getDisplayLanguage().equals("English")) {
            return 0;
        } else return 1;

    }
}
