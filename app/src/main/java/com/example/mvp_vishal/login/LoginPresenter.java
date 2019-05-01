package com.example.mvp_vishal.login;


import android.text.TextUtils;

import com.example.mvp_vishal.application.AppResponse;
import com.example.mvp_vishal.networking.ErrorHandler;
import com.example.mvp_vishal.networking.NetworkError;
import com.example.mvp_vishal.networking.NetworkService;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseView;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

class LoginPresenter {
    private SessionManager sessionManager;
    private NetworkService networkService;
    private CompositeDisposable mCompositeDisposable;
    private LoginView loginView;

    @Inject
    LoginPresenter(NetworkService networkService, SessionManager sessionManager,
                   BaseView baseView) {
        this.sessionManager = sessionManager;
        this.loginView = (LoginView) baseView;
        this.networkService = networkService;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    void doLogin(String mobile, String pass) {
        loginView.hideKeyboard();
        if (TextUtils.isEmpty(mobile)) {
            loginView.emailError();
        } else if (TextUtils.isEmpty(pass)) {
            loginView.passwordError();
        } else {
            loginView.showProgress();
            mCompositeDisposable.add(
                    networkService.requestLogin(mobile, pass).subscribe(new Consumer<AppResponse<Login>>() {
                        @Override
                        public void accept(@NonNull AppResponse<Login> loginResponse) throws Exception {
                            loginView.hideProgress();
                            if (ErrorHandler.isError(loginResponse.error)) {
                                loginView.loginSuccess(loginResponse.data, sessionManager);
                            } else {
                                loginView.loginError(loginResponse.message);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(@NonNull Throwable e) throws Exception {
                            loginView.hideProgress();
                            loginView.loginError(new NetworkError(e).getAppErrorMessage());
                        }
                    })
            );
        }
    }

    void onDestroy() {
        mCompositeDisposable.clear();
    }
}
