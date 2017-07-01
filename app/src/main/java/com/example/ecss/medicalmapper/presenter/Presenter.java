package com.example.ecss.medicalmapper.presenter;

import com.example.ecss.medicalmapper.data.network.service.Service;
import com.example.ecss.medicalmapper.data.repository.Repository;
import com.example.ecss.medicalmapper.presenter.view.View;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by khalifa on 22/06/17.
 */

public class Presenter {

    protected View mView;
    protected Service mService;
    protected Repository mRepository;
    protected CompositeSubscription mCompositeSubscription;

    public Presenter(View view, Service service, Repository repository) {
        mView = view;
        mService = service;
        mRepository = repository;
        mCompositeSubscription = new CompositeSubscription();
    }

    public void onDestroy() {
        mCompositeSubscription.unsubscribe();
    }
}
