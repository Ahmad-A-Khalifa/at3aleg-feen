package com.example.ecss.medicalmapper.models;


import java.io.Serializable;

public abstract class MedicalPlace implements Serializable {
    public int id;
    public String Name;
    public String NameArabic;
    public String BuildingNumber;
    public String Street;
    public String StreetArabic;
    public String AddressNotes;
    public String AddressNotesArabic;
    public String PhoneNumber;
    public double Latitude;
    public double Longitude;
}
