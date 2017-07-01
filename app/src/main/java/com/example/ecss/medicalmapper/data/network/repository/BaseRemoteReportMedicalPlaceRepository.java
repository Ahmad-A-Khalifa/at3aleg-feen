package com.example.ecss.medicalmapper.data.network.repository;

import com.example.ecss.medicalmapper.data.repository.ReportMedicalPlaceRepository;

/**
 * Created by khalifa on 23/06/17.
 */

public interface BaseRemoteReportMedicalPlaceRepository extends ReportMedicalPlaceRepository {
    boolean reportMedicalPlace(int userId, int placeId, int branchId, String state, String comment) throws Exception;
}
