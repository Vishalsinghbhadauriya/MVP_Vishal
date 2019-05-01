package com.example.mvp_vishal.application;


import android.app.Application;

import com.example.mvp_vishal.splash.SplashActivity;

public class MVPApplication extends Application {

    private AppComponent component;

    public static MVPApplication get(SplashActivity context) {

        return ((MVPApplication) context.getApplicationContext());

    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
    }

    private void buildComponent() {
        component = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
