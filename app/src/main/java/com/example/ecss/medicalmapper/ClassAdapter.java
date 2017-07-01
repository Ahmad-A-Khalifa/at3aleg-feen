package com.example.ecss.medicalmapper;

import com.example.ecss.medicalmapper.model.place.Branch;
import com.example.ecss.medicalmapper.model.place.Clinic;
import com.example.ecss.medicalmapper.model.place.Hospital;
import com.example.ecss.medicalmapper.model.place.Laboratory;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.model.place.Pharmacy;
import com.example.ecss.medicalmapper.network.advancedSearchApiCall.AdvancedSearchBranch;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.PlacesSearchBranch;

import java.util.ArrayList;
import java.util.List;

public class ClassAdapter {

    public static MedicalPlace convertAdvancedSearchBranchToMedicalPlace(AdvancedSearchBranch branch, Integer num) {
        List<Branch> oneBranchList = new ArrayList<>();

        Branch oneBranch = new Branch();
        oneBranch.setBranchId(branch.getBranchId());
        oneBranch.setBranchType(branch.getBranchType());
        oneBranch.setBranchStreetName(branch.getBranchStreetName());
        oneBranch.setBranchBuildingNum(branch.getBranchBuildingNum());
        oneBranch.setBranchFloorNum(branch.getBranchFloorNum());
        oneBranch.setBranchAppartmentNum(branch.getBranchAppartmentNum());
        oneBranch.setBranchAddressNotes(branch.getBranchAddressNotes());
        oneBranch.setBranchLongitude(branch.getBranchLongitude());
        oneBranch.setBranchLatitude(branch.getBranchLatitude());
        oneBranch.setBranchRate(String.valueOf(branch.getBranchRate()));
        oneBranch.setAppointments(branch.getAppointments());
        oneBranch.setBranchPhoneNum(branch.getPhoneNumber());
        oneBranch.setBranchLanguage(num);

        oneBranchList.add(oneBranch);


        if (branch.getBranchType().equals("hospital")) {

            Hospital hospital = new Hospital();

            hospital.setHospitalId(branch.getPlaceId());
            hospital.setHospitalName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            hospital.setIsGovernment(true);
            hospital.setHospitalEmergency(true);
            hospital.setBranches(oneBranchList);

            return hospital;

        } else if (branch.getBranchType().equals("lab")) {
            Laboratory lab = new Laboratory();

            lab.setLabId(branch.getPlaceId());
            lab.setLabName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            lab.setLabSpecialization((num == 0) ? branch.getSpecializationEN() : branch.getSpecializationAR());
            lab.setLabHotline("");
            lab.setBranches(oneBranchList);

            return lab;

        } else if (branch.getBranchType().equals("pharmacy")) {
            Pharmacy pharmacy = new Pharmacy();

            pharmacy.setPharmacyId(branch.getPlaceId());
            pharmacy.setPharmacyName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            pharmacy.setPharmacyHotline("");
            pharmacy.setPharmacyDelivery(true);
            pharmacy.setBranches(oneBranchList);

            return pharmacy;

        } else if (branch.getBranchType().equals("clinic")) {
            Clinic clinic = new Clinic();

            clinic.setClinicId(branch.getPlaceId());
            clinic.setClinicName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            clinic.setClinicSpecialization((num == 0) ? branch.getSpecializationEN() : branch.getSpecializationAR());
            clinic.setBranches(oneBranchList);

            return clinic;
        }
        return null;
    }

    public static MedicalPlace convertSearchPlaceBranchToMedicalPlace(PlacesSearchBranch branch, Integer num) {

        List<Branch> oneBranchList = new ArrayList<>();

        Branch oneBranch = new Branch();
        oneBranch.setBranchId(branch.getBranchId());
        oneBranch.setBranchType(branch.getBranchType());
        oneBranch.setBranchStreetName(branch.getBranchStreetName());
        oneBranch.setBranchBuildingNum(branch.getBranchBuildingNum());
        oneBranch.setBranchFloorNum(branch.getBranchFloorNum());
        oneBranch.setBranchAppartmentNum(branch.getBranchAppartmentNum());
        oneBranch.setBranchAddressNotes(branch.getBranchAddressNotes());
        oneBranch.setBranchPhoneNum(branch.getPhoneNumber());
        oneBranch.setBranchLongitude(branch.getBranchLongitude());
        oneBranch.setBranchLatitude(branch.getBranchLatitude());
        oneBranch.setBranchRate(branch.getBranchRate());
        oneBranch.setAppointments(branch.getAppointments());
        oneBranch.setBranchLanguage(branch.getBranchLanguage());

        oneBranchList.add(oneBranch);


        if (branch.getBranchType().equals("hospital")) {

            Hospital hospital = new Hospital();

            hospital.setHospitalId(branch.getHospitalId());
            hospital.setHospitalName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            hospital.setIsGovernment(true);
            hospital.setHospitalEmergency(true);
            hospital.setBranches(oneBranchList);

            return hospital;

        } else if (branch.getBranchType().equals("lab")) {
            Laboratory lab = new Laboratory();

            lab.setLabId(branch.getLabId());
            lab.setLabName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            lab.setLabSpecialization((num == 0) ? branch.getSpecializationEN() : branch.getSpecializationAR());
            lab.setLabHotline("");
            lab.setBranches(oneBranchList);

            return lab;

        } else if (branch.getBranchType().equals("pharmacy")) {
            Pharmacy pharmacy = new Pharmacy();

            pharmacy.setPharmacyId(branch.getPharmacyId());
            pharmacy.setPharmacyName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            pharmacy.setPharmacyHotline("");
            pharmacy.setPharmacyDelivery(true);
            pharmacy.setBranches(oneBranchList);

            return pharmacy;

        } else if (branch.getBranchType().equals("clinic")) {
            Clinic clinic = new Clinic();

            clinic.setClinicId(branch.getClinicId());
            clinic.setClinicName((num == 0) ? branch.getPlaceNameEN() : branch.getPlaceNameAR());
            clinic.setClinicSpecialization((num == 0) ? branch.getSpecializationEN() : branch.getSpecializationAR());
            clinic.setBranches(oneBranchList);

            return clinic;
        }
        return null;
    }
}
