package com.example.ecss.medicalmapper.presenter.view;

/**
 * Created by khalifa on 22/06/17.
 */

public interface View {
    void onSuccess(int requestId);
    void onFailure(int requestId, int errorCode, String errorMessage);
}
