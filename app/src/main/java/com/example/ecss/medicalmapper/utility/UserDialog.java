package com.example.ecss.medicalmapper.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.example.ecss.medicalmapper.R;


public class UserDialog {

    private UserDialog(){}

    public static void showMessage(View view, String message){
        if (message != null) {
            showMessage(view, message, Snackbar.LENGTH_LONG);
        }
    }

    public static void showMessage(View view, String message, int duration){
        Snackbar.make(view, message, duration).show();
    }

    public static ProgressDialog showProgressDialog(Context context) {
        if (context != null) {
            return ProgressDialog.show(
                    context, null, context.getString(R.string.loading), true, true);
        }
        return null;
    }
}
