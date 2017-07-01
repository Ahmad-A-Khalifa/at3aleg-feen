package com.example.ecss.medicalmapper.model.appointment;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Appointment implements Parcelable/*extends RealmObject*/ {

    public static final Creator<Appointment> CREATOR = new Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
    @SerializedName("appointments_day")
    private String mAppointmentDay;
    @SerializedName("appointments_from")
    private Integer mAppointmentFrom;
    @SerializedName("appointments_to")
    private Integer mAppointmentTo;

    public Appointment() {

    }

    public Appointment(Parcel in) {
        this.mAppointmentDay = in.readString();
        this.mAppointmentFrom = in.readInt();
        this.mAppointmentTo = in.readInt();
    }

    public String getAppointmentDay() {
        return mAppointmentDay;
    }

    public void setAppointmentDay(String AppointmentDay) {
        this.mAppointmentDay = AppointmentDay;
    }

    public Integer getAppointmentFrom() {
        return mAppointmentFrom;
    }

    public void setAppointmentFrom(Integer AppointmentFrom) {
        this.mAppointmentFrom = AppointmentFrom;
    }

    public Integer getAppointmentTo() {
        return mAppointmentTo;
    }

    public void setAppointmentTo(Integer AppointmentTo) {
        this.mAppointmentTo = AppointmentTo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(mAppointmentDay);
        dest.writeInt(mAppointmentFrom);
        dest.writeInt(mAppointmentTo);
    }
}
