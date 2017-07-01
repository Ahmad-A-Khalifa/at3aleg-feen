package com.example.ecss.medicalmapper.storage.databases;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;

public class RealmController {

    private static RealmController mInstance;
    private final Realm mRealm;

    public RealmController(Application application) {
        mRealm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (mInstance == null) {
            mInstance = new RealmController(fragment.getActivity().getApplication());
        }
        return mInstance;
    }

    public static RealmController with(Activity activity) {

        if (mInstance == null) {
            mInstance = new RealmController(activity.getApplication());
        }
        return mInstance;
    }

    public static RealmController with(Application application) {

        if (mInstance == null) {
            mInstance = new RealmController(application);
        }
        return mInstance;
    }

    public static RealmController getmInstance() {

        return mInstance;
    }

    public Realm getRealm() {

        return mRealm;
    }

    //Refresh the mRealm mInstance
    /*public void refresh() {
        mRealm.waitForChange();
    }

    public void saveClinic(Clinic clinic) {
        mRealm.beginTransaction();
        mRealm.copyToRealm(clinic);
        mRealm.commitTransaction();
    }

    public void savePharmacy(Pharmacy pharmacy) {

        mRealm.beginTransaction();
        mRealm.copyToRealm(pharmacy);
        mRealm.commitTransaction();
    }

    public void saveLab(Laboratory lab) {

        mRealm.beginTransaction();
        mRealm.copyToRealm(lab);
        mRealm.commitTransaction();
    }

    public void saveHospital(Hospital hospital) {

        mRealm.beginTransaction();
        mRealm.copyToRealm(hospital);
        mRealm.commitTransaction();
    }


    public RealmResults<Clinic> getClinics() {

        return mRealm.where(Clinic.class).findAll();
    }

    public RealmResults<Pharmacy> getPharmacies() {

        return mRealm.where(Pharmacy.class).findAll();
    }

    public RealmResults<Laboratory> getLabs() {

        return mRealm.where(Laboratory.class).findAll();
    }

    public RealmResults<Hospital> getHospitals() {

        return mRealm.where(Hospital.class).findAll();
    }*/
}
