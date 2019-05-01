package com.example.mvp_vishal;

import com.example.mvp_vishal.login.LoginActivity;
import com.example.mvp_vishal.logout.LogoutActivity;
import com.example.mvp_vishal.scopes.PerActivity;
import com.example.mvp_vishal.splash.SplashActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(SplashActivity activity);

    void inject(LogoutActivity activity);

    void inject(LoginActivity activity);
}
