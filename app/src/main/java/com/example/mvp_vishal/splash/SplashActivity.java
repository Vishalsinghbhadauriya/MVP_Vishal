package com.example.mvp_vishal.splash;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mvp_vishal.ActivityModule;
import com.example.mvp_vishal.R;
import com.example.mvp_vishal.application.MVPApplication;
import com.example.mvp_vishal.login.LoginActivity;
import com.example.mvp_vishal.logout.LogoutActivity;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter presenter;
    @Inject
    public SessionManager mSessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initPresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.startSplashProcessing();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.stopSplashProcessing();
    }

    private void initPresenter() {
        MVPApplication.get(this)
                .getComponent()
                .activityComponent(new ActivityModule(this))
                .inject(this);
    }

    @Override
    public void finishedWithBackGroundProcessing() {
        if (!mSessionManager.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, LogoutActivity.class));
        }
        overridePendingTransition(0, 0);
        finish();
    }
}
