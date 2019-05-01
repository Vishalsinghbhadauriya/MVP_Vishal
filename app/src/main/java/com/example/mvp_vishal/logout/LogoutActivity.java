package com.example.mvp_vishal.logout;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mvp_vishal.ActivityModule;
import com.example.mvp_vishal.R;
import com.example.mvp_vishal.application.MVPApplication;
import com.example.mvp_vishal.login.LoginActivity;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseActivity;

import javax.inject.Inject;


public class LogoutActivity extends BaseActivity implements LogoutView {

    private Button btnLogout;

    @Inject
    LogoutPresenter logoutPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        initUI();
        setUpPresenter();
    }

    private void initUI() {
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutPresenter.doLogout();
            }
        });
    }

    private void setUpPresenter() {
        MVPApplication.get(this)
                .getComponent()
                .activityComponent(new ActivityModule(this))
                .inject(this);
    }

    @Override
    public void showProgress() {
        showProgressDialog();
    }

    @Override
    public void hideProgress() {
        dismissProgressDialog();
    }

    @Override
    public void onLogoutError(String error) {
        Snackbar.make(btnLogout, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLogoutSuccess(SessionManager mSessionManager) {
        mSessionManager.logout();
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logoutPresenter.onDestroy();
    }
}
