package com.example.ecss.medicalmapper.data.network.repository;

import android.util.Log;

import com.example.ecss.medicalmapper.data.network.exception.HTTPException;
import com.example.ecss.medicalmapper.data.network.payload.StatusResponsePayload;
import com.example.ecss.medicalmapper.data.network.rest.RestClient;
import com.example.ecss.medicalmapper.data.network.util.HTTPResponseCode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RemoteReportMedicalPlaceRepository implements BaseRemoteReportMedicalPlaceRepository {

    private String mBaseUrl;

    public RemoteReportMedicalPlaceRepository(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    @Override
    public boolean reportMedicalPlace(int userId,
                                      int placeId,
                                      int branchId,
                                      String state,
                                      String comment) throws Exception {
        final String WEBSERVICE_URL = "%s/user/report/medical/place";
        String url = String.format(Locale.ENGLISH, WEBSERVICE_URL, mBaseUrl);
        List<RestClient.DataPair> requestBodyPayload = new ArrayList<>();
        requestBodyPayload.add(new RestClient.DataPair("user_id", userId));
        requestBodyPayload.add(new RestClient.DataPair("place_id", placeId));
        requestBodyPayload.add(new RestClient.DataPair("branch_id", branchId));
        requestBodyPayload.add(new RestClient.DataPair("place_state", state));
        requestBodyPayload.add(new RestClient.DataPair("user_comment", comment));
        RestClient restClient = new RestClient(url, requestBodyPayload);
        restClient.execute(RestClient.MethodType.POST);
        if (restClient.getResponseCode() == HTTPResponseCode.OK) {
            StatusResponsePayload.Status responseStatus = new Gson()
                    .fromJson(restClient.getResponse(), StatusResponsePayload.class)
                    .getStatus();
            if (responseStatus.isSuccessful()) {

                return true;
            }
            else {
                throw new Exception(responseStatus.getErrorMessage());
            }
        }
        else {
            String message;
            switch (restClient.getResponseCode()) {
                case HTTPResponseCode.BAD_REQUEST:
                    message = "Bad request";
                    break;
                case HTTPResponseCode.UNAUTHORIZED:
                    message = "Unauthorized request";
                    break;
                case HTTPResponseCode.UNKNOWN:
                default:
                    message = "Unknown error";
                    break;
            }
            String errorMessage = restClient.getErrorMessage() + message;
            Log.e("Repository Exception", errorMessage);
            throw new HTTPException(
                    restClient.getResponseCode(),
                    errorMessage
            );
        }
    }
}
