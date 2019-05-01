package com.example.mvp_vishal.networking;

import com.example.mvp_vishal.application.AppResponse;
import com.example.mvp_vishal.login.Login;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetworkService {
    private final NetworkRequest networkRequest;

    public NetworkService(NetworkRequest networkRequest) {
        this.networkRequest = networkRequest;
    }

    public Observable<AppResponse<Login>> requestLogin(String mobile, String password) {
        return networkRequest.requestLogin(mobile, password)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public Observable<AppResponse> requestLogout() {
        return networkRequest.requestLogout()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}

