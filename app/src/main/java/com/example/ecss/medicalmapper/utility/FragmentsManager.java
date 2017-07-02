package com.example.ecss.medicalmapper.utility;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by khalifa on 30/06/17.
 */

public class FragmentsManager {
    public static void startFragment(FragmentActivity activity,
                                     View placeHolderView,
                                     Object fragment){
        if (activity != null && fragment != null){
            android.app.Fragment oldFragment =
                    activity.getFragmentManager().findFragmentById(placeHolderView.getId());
            if (oldFragment != null) {
                activity.getFragmentManager().beginTransaction().remove(oldFragment).commit();
            }
            if (fragment instanceof Fragment){
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(placeHolderView.getId(), (Fragment)fragment)
                        .commit();
            }
            else if (fragment instanceof android.app.Fragment) {
                activity.getFragmentManager()
                        .beginTransaction()
                        .replace(placeHolderView.getId(), (android.app.Fragment)fragment)
                        .commit();
            }
        }
    }
}
