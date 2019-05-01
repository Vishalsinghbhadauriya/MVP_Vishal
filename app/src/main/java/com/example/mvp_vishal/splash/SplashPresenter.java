package com.example.mvp_vishal.splash;

import android.os.Handler;

import com.example.mvp_vishal.view.BaseView;

import javax.inject.Inject;

public class SplashPresenter {
    private static final int SPLASH_TIMEOUT = 2000;
    private SplashView splashView;
    private Handler handler;
    private Runnable runnable;

    @Inject
    public SplashPresenter(BaseView baseView) {
        this.splashView = (SplashView) baseView;
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                SplashPresenter.this.splashView.finishedWithBackGroundProcessing();
            }
        };
    }

    public void startSplashProcessing() {
        // mock splash processing.
        handler.postDelayed(runnable, SPLASH_TIMEOUT);
    }

    public void stopSplashProcessing() {
        handler.removeCallbacks(runnable);
    }
}
