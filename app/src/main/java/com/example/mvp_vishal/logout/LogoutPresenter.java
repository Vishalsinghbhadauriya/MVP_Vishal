package com.example.mvp_vishal.logout;

import android.support.annotation.NonNull;

import com.example.mvp_vishal.application.AppResponse;
import com.example.mvp_vishal.networking.ErrorHandler;
import com.example.mvp_vishal.networking.NetworkError;
import com.example.mvp_vishal.networking.NetworkService;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseView;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

class LogoutPresenter {
    private SessionManager sessionManager;
    private NetworkService networkService;
    private CompositeDisposable mCompositeDisposable;

    private LogoutView logoutView;

    @Inject
    LogoutPresenter(NetworkService networkService, SessionManager sessionManager,
                    BaseView baseView) {
        this.sessionManager = sessionManager;
        this.logoutView = (LogoutView) baseView;
        this.networkService = networkService;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    void doLogout() {
        logoutView.showProgress();
        mCompositeDisposable.add(networkService.requestLogout().subscribe(new Consumer<AppResponse>() {
            @Override
            public void accept(@NonNull AppResponse logoutResponse) throws Exception {
                logoutView.hideProgress();
                if (ErrorHandler.isError(logoutResponse.error)) {
                    logoutView.onLogoutSuccess(sessionManager);
                } else {
                    logoutView.onLogoutError(logoutResponse.message);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable e) throws Exception {
                logoutView.hideProgress();
                logoutView.onLogoutError(new NetworkError(e).getAppErrorMessage());
            }
        }));
    }

    void onDestroy() {
        mCompositeDisposable.clear();
    }
}
