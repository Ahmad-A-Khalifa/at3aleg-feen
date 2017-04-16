package com.example.ecss.medicalmapper.model.appointment;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class Appointment extends RealmObject {

    @SerializedName("appointments_day")
    private String mAppointmentDay;

    @SerializedName("appointments_from")
    private Integer mAppointmentFrom;

    @SerializedName("appointments_to")
    private Integer mAppointmentTo;

    public String getmAppointmentDay() {
        return mAppointmentDay;
    }

    public void setmAppointmentDay(String mAppointmentDay) {
        this.mAppointmentDay = mAppointmentDay;
    }

    public Integer getmAppointmentFrom() {
        return mAppointmentFrom;
    }

    public void setmAppointmentFrom(Integer mAppointmentFrom) {
        this.mAppointmentFrom = mAppointmentFrom;
    }

    public Integer getmAppointmentTo() {
        return mAppointmentTo;
    }

    public void setmAppointmentTo(Integer mAppointmentTo) {
        this.mAppointmentTo = mAppointmentTo;
    }
}
