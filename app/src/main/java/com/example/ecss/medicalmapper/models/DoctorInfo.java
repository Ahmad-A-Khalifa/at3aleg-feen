package com.example.ecss.medicalmapper.models;

public class DoctorInfo {
    private String specialization;
    private String degree;
    private String overview;

    public static String[] SPECIALIZATION = new String[]{
            "Allergy and Immunology",
            "Andrology and Male Infertility",
            "Audiology",
            "Cardiology and Thoracic Surgery (Heart & Chest)",
            "Cardiology and Vascular Disease (Heart)",
            "Chest and Respiratory",
            "Dentistry (Teeth)",
            "Dermatology (Skin)",
            "Diabetes and Endocrinology",
            "Dietitian and Nutrition",
            "Ear, Nose, and Throat",
            "Elders",
            "Gastroenterology and Endoscopy",
            "General Practice",
            "General Surgery",
            "Gynaecology and Infertility",
            "Hematology",
            "Hematology (Liver)",
            "IVF and Infertility",
            "Nephrology",
            "Neurology",
            "Neurology (Brain & Nerves Surgery)",
            "Obesity and Laparoscopic Surgery",
            "Oncology (Tumor)",
            "Ophthalmology (Eyes)",
            "Orthopedics (Bones)",
            "Pain Management",
            "Pediatric Surgery",
            "Pediatric and New Born (Child)",
            "Phoniatrics (Speech)",
            "Physiotherapy and Sport Injuries",
            "Plastic Surgery",
            "Psychiatry (Mental, Emotional or Behavioral Disorders)",
            "Rheumatology",
            "Spinal Surgery",
            "Urology (Urinary System)",
            "Vascular Surgery (Arteries and Vein Surgery)"
    };
    public static String[] DEGREE = new String[]{
            "Consultant",
            "Doctorate",
            "Fellowship",
            "Professor",
            "Professor Assistant",
            "Specialist",
            "Teacher"
    };

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setSpecialization(int specialization) {
        this.specialization = SPECIALIZATION[specialization];
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public void setDegree(int degree) {
        this.degree = DEGREE[degree];
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
