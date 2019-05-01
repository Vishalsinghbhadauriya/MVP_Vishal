package com.example.mvp_vishal;

import com.example.mvp_vishal.login.LoginActivity;
import com.example.mvp_vishal.scopes.PerActivity;
import com.example.mvp_vishal.view.BaseView;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private LoginActivity baseView;

    public ActivityModule(LoginActivity baseView) {
        this.baseView = baseView;
    }

    @PerActivity
    @Provides
    public BaseView providesSplashView() {
        return (BaseView) baseView;
    }
}
