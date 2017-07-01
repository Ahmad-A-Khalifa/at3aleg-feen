package com.example.ecss.medicalmapper.data.network.service;


import com.example.ecss.medicalmapper.data.network.exception.HTTPException;
import com.example.ecss.medicalmapper.data.network.repository.RemoteReportMedicalPlaceRepository;
import com.example.ecss.medicalmapper.utility.ErrorCode;

import java.util.concurrent.Callable;

import rx.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class ReportMedicalPlaceService implements Service {


    public Observable<Void> getReportMedicalPlaceObservable(
            final RemoteReportMedicalPlaceRepository remoteRepository,
            final int userId,
            final int placeId,
            final int branchId,
            final String state,
            final String comment) {
        return Observable.fromCallable(
                new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        if (remoteRepository == null) {
                            throw new Exception("Repository cannot be null");
                        }
                        remoteRepository.reportMedicalPlace(
                                userId,
                                placeId,
                                branchId,
                                state,
                                comment
                        );
                        return null;
                    }
                }
        );
    }

    public Subscription getReportMedicalPlaceSubscription(
            Observable<Void> observable, final ReportMedicalPlaceCallback callback) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<Void>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                int responseCode = ErrorCode.ERROR_REPORTING_MEDICAL_PLACE;
                                if (e instanceof HTTPException) {
                                    responseCode = ((HTTPException) e).getResponseCode();
                                }
                                if (callback != null) {
                                    callback.onFailure(responseCode, e.getMessage());
                                }
                            }

                            @Override
                            public void onNext(Void aVoid) {
                                if (callback != null) {
                                    callback.onSuccess();
                                }
                            }
                        }
                );
    }

    public interface ReportMedicalPlaceCallback {
        void onSuccess();

        void onFailure(int errorCode, String errorMessage);
    }
}
