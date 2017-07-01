package com.example.ecss.medicalmapper.presenter;

import com.example.ecss.medicalmapper.data.network.repository.RemoteReportMedicalPlaceRepository;
import com.example.ecss.medicalmapper.data.network.service.ReportMedicalPlaceService;
import com.example.ecss.medicalmapper.data.network.util.BaseInternetConnectionChecker;
import com.example.ecss.medicalmapper.presenter.view.ReportMedicalPlaceView;
import com.example.ecss.medicalmapper.utility.ErrorCode;

import rx.Observable;
import rx.Subscription;

public class ReportMedicalPlacePresenter extends Presenter {

    private ReportMedicalPlaceView mReportPlaceView;
    private ReportMedicalPlaceService mReportService;
    private RemoteReportMedicalPlaceRepository mReportRepository;
    private BaseInternetConnectionChecker mInternetConnectionChecker;

    public ReportMedicalPlacePresenter(ReportMedicalPlaceView reportPlaceView,
                                       ReportMedicalPlaceService reportService,
                                       RemoteReportMedicalPlaceRepository reportRepository,
                                       BaseInternetConnectionChecker internetConnectionChecker) {
        super(reportPlaceView, reportService, reportRepository);
        mReportPlaceView = reportPlaceView;
        mReportService = reportService;
        mReportRepository = reportRepository;
        mInternetConnectionChecker = internetConnectionChecker;
    }

    public void reportMedicalPlace(final int requestId,
                                   int userId,
                                   int placeId,
                                   int branchId,
                                   String state,
                                   String comment) {
        if (mInternetConnectionChecker != null && mInternetConnectionChecker.isNetworkAvailable()) {
            if (mReportService != null && mReportRepository != null && state != null) {
                Observable<Void> observable = mReportService.getReportMedicalPlaceObservable(
                        mReportRepository,
                        userId,
                        placeId,
                        branchId,
                        state,
                        comment
                );
                Subscription subscription = mReportService.getReportMedicalPlaceSubscription(
                        observable,
                        new ReportMedicalPlaceService.ReportMedicalPlaceCallback() {
                            @Override
                            public void onSuccess() {
                                if (mReportPlaceView != null) {
                                    mReportPlaceView.onSuccess(requestId);
                                }
                            }

                            @Override
                            public void onFailure(int errorCode, String errorMessage) {
                                if (mReportPlaceView != null) {
                                    mReportPlaceView.onFailure(requestId, errorCode, errorMessage);
                                }
                            }
                        }
                );
                mCompositeSubscription.add(subscription);
            }
        }
        else {
            if (mReportPlaceView != null) {
                mReportPlaceView.onFailure(requestId, ErrorCode.ERROR_NO_NETWORK_CONNECTION, "");
            }
        }
    }
}
