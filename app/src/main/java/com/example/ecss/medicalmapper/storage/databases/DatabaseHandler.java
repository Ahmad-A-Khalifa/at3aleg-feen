package com.example.ecss.medicalmapper.storage.databases;

public class DatabaseHandler {
/*
    private final String LOG_TAG = DatabaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "places";

    private static final String TABLE_HOSPITALS = "Hospitals";
    private static final String TABLE_PHARMACIES = "Pharmacies";
    private static final String TABLE_CLINICS = "Clinics";
    private static final String TABLE_LABORATORIES = "Laboratories";

    private static final String ID = "id";
    private static final String NAME = "Name";
    private static final String NAME_ARABIC = "NameArabic";
    private static final String BUILDING_NUMBER = "BuildingNumber";
    private static final String STREET = "Street";
    private static final String STREET_ARABIC = "StreetArabic";
    private static final String ADDRESS_NOTES = "AddressNotes";
    private static final String ADDRESS_NOTES_ARABIC = "AddressNotesArabic";
    private static final String PHONE_NUMBER = "PhoneNumber";
    private static final String LATITUDE = "Latitude";
    private static final String LONGITUDE = "Longitude";

    private static final String SPECIALIZATION = "Specialization";
    private static final String SPECIALIZATION_ARABIC = "SpecializationArabic";
    private static final String DOCTOR = "Doctor";
    private static final String DOCTOR_ARABIC = "DoctorArabic";
    private static final String APPRATMENT_NUMBER = "AppartmentNumber";
    private static final String APPOINTMENTS = "Appointment";
    private static final String CLOSED_DAYS = "ClosedDays";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_HOSPITALS_TABLE = "CREATE TABLE " + TABLE_HOSPITALS + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + NAME_ARABIC + " TEXT,"
                + BUILDING_NUMBER + " TEXT," + STREET + " TEXT," + STREET_ARABIC + " TEXT," + ADDRESS_NOTES + " TEXT,"
                + ADDRESS_NOTES_ARABIC + " TEXT," + PHONE_NUMBER + " TEXT," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE,"
                + SPECIALIZATION + " TEXT," + SPECIALIZATION_ARABIC + " TEXT," + DOCTOR + " TEXT," + DOCTOR_ARABIC + " TEXT" + ")";

        String CREATE_PHARMACIES_TABLE = "CREATE TABLE " + TABLE_PHARMACIES + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + NAME_ARABIC + " TEXT,"
                + BUILDING_NUMBER + " TEXT," + STREET + " TEXT," + STREET_ARABIC + " TEXT," + ADDRESS_NOTES + " TEXT,"
                + ADDRESS_NOTES_ARABIC + " TEXT," + PHONE_NUMBER + " TEXT," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE" + ")";

        String CREATE_CLINICS_TABLE = "CREATE TABLE " + TABLE_CLINICS + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + NAME_ARABIC + " TEXT,"
                + BUILDING_NUMBER + " TEXT," + STREET + " TEXT," + STREET_ARABIC + " TEXT," + ADDRESS_NOTES + " TEXT,"
                + ADDRESS_NOTES_ARABIC + " TEXT," + PHONE_NUMBER + " TEXT," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE,"
                + SPECIALIZATION + " TEXT," + SPECIALIZATION_ARABIC + " TEXT," + DOCTOR + " TEXT," + DOCTOR_ARABIC + " TEXT,"
                + CLOSED_DAYS + " TEXT," + APPOINTMENTS + " TEXT," + APPRATMENT_NUMBER + " TEXT" + ")";

        String CREATE_LABORATORIES_TABLE = "CREATE TABLE " + TABLE_LABORATORIES + "("
                + ID + " INTEGER PRIMARY KEY," + NAME + " TEXT," + NAME_ARABIC + " TEXT,"
                + BUILDING_NUMBER + " TEXT," + STREET + " TEXT," + STREET_ARABIC + " TEXT," + ADDRESS_NOTES + " TEXT,"
                + ADDRESS_NOTES_ARABIC + " TEXT," + PHONE_NUMBER + " TEXT," + LATITUDE + " DOUBLE," + LONGITUDE + " DOUBLE,"
                + SPECIALIZATION + " TEXT," + SPECIALIZATION_ARABIC + " TEXT," + DOCTOR + " TEXT," + DOCTOR_ARABIC + " TEXT,"
                + CLOSED_DAYS + " TEXT," + APPOINTMENTS + " TEXT," + APPRATMENT_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_HOSPITALS_TABLE);
        db.execSQL(CREATE_PHARMACIES_TABLE);
        db.execSQL(CREATE_CLINICS_TABLE);
        db.execSQL(CREATE_LABORATORIES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOSPITALS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLINICS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LABORATORIES);

        // Create tables again
        onCreate(db);
    }

    public void addHospital(Hospital place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, place.Name);
        values.put(NAME_ARABIC, place.NameArabic);
        values.put(BUILDING_NUMBER, place.BuildingNumber);
        values.put(STREET, place.Street);
        values.put(STREET_ARABIC, place.StreetArabic);
        values.put(ADDRESS_NOTES, place.AddressNotes);
        values.put(ADDRESS_NOTES_ARABIC, place.AddressNotesArabic);
        values.put(PHONE_NUMBER, place.PhoneNumber);
        values.put(LATITUDE, place.Latitude);
        values.put(LONGITUDE, place.Longitude);

        values.put(SPECIALIZATION, place.Specialization);
        values.put(SPECIALIZATION_ARABIC, place.SpecializationArabic);
        values.put(DOCTOR, place.Doctor);
        values.put(DOCTOR_ARABIC, place.DoctorArabic);

        db.insert(TABLE_HOSPITALS, null, values);
        db.close();
    }

    public void addClinic(Clinic place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(NAME, place.Name);
        values.put(NAME_ARABIC, place.NameArabic);
        values.put(BUILDING_NUMBER, place.BuildingNumber);
        values.put(STREET, place.Street);
        values.put(STREET_ARABIC, place.StreetArabic);
        values.put(ADDRESS_NOTES, place.AddressNotes);
        values.put(ADDRESS_NOTES_ARABIC, place.AddressNotesArabic);
        values.put(PHONE_NUMBER, place.PhoneNumber);
        values.put(LATITUDE, place.Latitude);
        values.put(LONGITUDE, place.Longitude);

        values.put(SPECIALIZATION, place.Specialization);
        values.put(SPECIALIZATION_ARABIC, place.SpecializationArabic);
        values.put(DOCTOR, place.Doctor);
        values.put(DOCTOR_ARABIC, place.DoctorArabic);

        values.put(APPRATMENT_NUMBER, place.AppartmentNumber);
        values.put(APPOINTMENTS, place.Appointment);
        values.put(CLOSED_DAYS, place.ClosedDays);

        db.insert(TABLE_CLINICS, null, values);
        db.close();
    }

    public void addPharmacy(Pharmacy place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, place.Name);
        values.put(NAME_ARABIC, place.NameArabic);
        values.put(BUILDING_NUMBER, place.BuildingNumber);
        values.put(STREET, place.Street);
        values.put(STREET_ARABIC, place.StreetArabic);
        values.put(ADDRESS_NOTES, place.AddressNotes);
        values.put(ADDRESS_NOTES_ARABIC, place.AddressNotesArabic);
        values.put(PHONE_NUMBER, place.PhoneNumber);
        values.put(LATITUDE, place.Latitude);
        values.put(LONGITUDE, place.Longitude);

        db.insert(TABLE_PHARMACIES, null, values);
        db.close();
    }

    public void addLaboratory(Laboratory place) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, place.Name);
        values.put(NAME_ARABIC, place.NameArabic);
        values.put(BUILDING_NUMBER, place.BuildingNumber);
        values.put(STREET, place.Street);
        values.put(STREET_ARABIC, place.StreetArabic);
        values.put(ADDRESS_NOTES, place.AddressNotes);
        values.put(ADDRESS_NOTES_ARABIC, place.AddressNotesArabic);
        values.put(PHONE_NUMBER, place.PhoneNumber);
        values.put(LATITUDE, place.Latitude);
        values.put(LONGITUDE, place.Longitude);

        values.put(SPECIALIZATION, place.Specialization);
        values.put(SPECIALIZATION_ARABIC, place.SpecializationArabic);
        values.put(DOCTOR, place.Doctor);
        values.put(DOCTOR_ARABIC, place.DoctorArabic);

        values.put(APPRATMENT_NUMBER, place.AppartmentNumber);
        values.put(APPOINTMENTS, place.Appointment);
        values.put(CLOSED_DAYS, place.ClosedDays);

        db.insert(TABLE_LABORATORIES, null, values);
        db.close();
    }


    public ArrayList<Hospital> getHospitals() {

        ArrayList<Hospital> hospitals = new ArrayList<Hospital>();

        String selectQuery = "SELECT  * FROM " + TABLE_HOSPITALS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Hospital hospital = new Hospital();

                hospital.id = Integer.parseInt(cursor.getString(0));
                hospital.Name = cursor.getString(1);
                hospital.NameArabic = cursor.getString(2);
                hospital.BuildingNumber = cursor.getString(3);
                hospital.Street = cursor.getString(4);
                hospital.StreetArabic = cursor.getString(5);
                hospital.AddressNotes = cursor.getString(6);
                hospital.AddressNotesArabic = cursor.getString(7);
                hospital.PhoneNumber = cursor.getString(8);
                hospital.Latitude = cursor.getDouble(9);
                hospital.Longitude = cursor.getDouble(10);
                hospital.Specialization = cursor.getString(11);
                hospital.SpecializationArabic = cursor.getString(12);
                hospital.Doctor = cursor.getString(13);
                hospital.DoctorArabic = cursor.getString(14);

                hospitals.add(hospital);
            } while (cursor.moveToNext());
        }

        return hospitals;
    }

    public ArrayList<Clinic> getClinics() {

        ArrayList<Clinic> clinics = new ArrayList<Clinic>();

        String selectQuery = "SELECT  * FROM " + TABLE_CLINICS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {

                Clinic clinic = new Clinic();

                clinic.id = Integer.parseInt(cursor.getString(0));
                clinic.Name = cursor.getString(1);
                clinic.NameArabic = cursor.getString(2);
                clinic.BuildingNumber = cursor.getString(3);
                clinic.Street = cursor.getString(4);
                clinic.StreetArabic = cursor.getString(5);
                clinic.AddressNotes = cursor.getString(6);
                clinic.AddressNotesArabic = cursor.getString(7);
                clinic.PhoneNumber = cursor.getString(8);
                clinic.Latitude = cursor.getDouble(9);
                clinic.Longitude = cursor.getDouble(10);
                clinic.Specialization = cursor.getString(11);
                clinic.SpecializationArabic = cursor.getString(12);
                clinic.Doctor = cursor.getString(13);
                clinic.DoctorArabic = cursor.getString(14);

                clinic.ClosedDays = cursor.getString(15);
                clinic.Appointment = cursor.getString(16);
                clinic.AppartmentNumber = cursor.getString(17);

                clinics.add(clinic);
            } while (cursor.moveToNext());
        }

        return clinics;
    }

    public ArrayList<Laboratory> getLaboratories() {

        ArrayList<Laboratory> laboratories = new ArrayList<Laboratory>();

        String selectQuery = "SELECT  * FROM " + TABLE_LABORATORIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {

                Laboratory laboratory = new Laboratory();

                laboratory.id = Integer.parseInt(cursor.getString(0));
                laboratory.Name = cursor.getString(1);
                laboratory.NameArabic = cursor.getString(2);
                laboratory.BuildingNumber = cursor.getString(3);
                laboratory.Street = cursor.getString(4);
                laboratory.StreetArabic = cursor.getString(5);
                laboratory.AddressNotes = cursor.getString(6);
                laboratory.AddressNotesArabic = cursor.getString(7);
                laboratory.PhoneNumber = cursor.getString(8);
                laboratory.Latitude = cursor.getDouble(9);
                laboratory.Longitude = cursor.getDouble(10);
                laboratory.Specialization = cursor.getString(11);
                laboratory.SpecializationArabic = cursor.getString(12);
                laboratory.Doctor = cursor.getString(13);
                laboratory.DoctorArabic = cursor.getString(14);

                laboratory.ClosedDays = cursor.getString(15);
                laboratory.Appointment = cursor.getString(16);
                laboratory.AppartmentNumber = cursor.getString(17);

                laboratories.add(laboratory);
            } while (cursor.moveToNext());
        }

        return laboratories;
    }

    public ArrayList<Pharmacy> getPharmacies() {

        ArrayList<Pharmacy> pharmacies = new ArrayList<Pharmacy>();

        String selectQuery = "SELECT  * FROM " + TABLE_PHARMACIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Pharmacy pharmacy = new Pharmacy();

                pharmacy.id = Integer.parseInt(cursor.getString(0));
                pharmacy.Name = cursor.getString(1);
                pharmacy.NameArabic = cursor.getString(2);
                pharmacy.BuildingNumber = cursor.getString(3);
                pharmacy.Street = cursor.getString(4);
                pharmacy.StreetArabic = cursor.getString(5);
                pharmacy.AddressNotes = cursor.getString(6);
                pharmacy.AddressNotesArabic = cursor.getString(7);
                pharmacy.PhoneNumber = cursor.getString(8);
                pharmacy.Latitude = cursor.getDouble(9);
                pharmacy.Longitude = cursor.getDouble(10);

                pharmacies.add(pharmacy);
            } while (cursor.moveToNext());
        }

        return pharmacies;
    }
    */
}
