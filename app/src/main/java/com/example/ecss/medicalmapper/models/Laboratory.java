package com.example.ecss.medicalmapper.models;

import java.io.Serializable;

/**
 * Created by ecss on 30/11/2016.
 */

public class Laboratory extends MedicalPlace implements Serializable {




    public String Doctor;
    public String DoctorArabic;
    public String Specialization;
    public String SpecializationArabic;

    public String AppartmentNumber;
    public String Appointments;
    public String ClosedDays;
}
