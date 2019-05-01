package com.example.mvp_vishal.login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvp_vishal.ActivityModule;
import com.example.mvp_vishal.R;
import com.example.mvp_vishal.application.MVPApplication;
import com.example.mvp_vishal.logout.LogoutActivity;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.utils.Utils;
import com.example.mvp_vishal.view.BaseActivity;

import javax.inject.Inject;
public class LoginActivity extends BaseActivity implements LoginView {


    private EditText etMobile, etPassword;
    private Button btnLogin;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initUI();
        setUpPresenter();
    }

    private void initUI() {
        etMobile = findViewById(R.id.etMobile);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenter.doLogin(etMobile.getText().toString().trim(), etPassword.getText().toString());
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
    public void emailError() {
        Snackbar.make(etMobile, R.string.err_mobile, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void passwordError() {
        Snackbar.make(etPassword, R.string.err_pass, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(Login login, SessionManager mSessionManager) {
        mSessionManager.saveLogin(login);
        Snackbar.make(btnLogin, getString(R.string.success), Snackbar.LENGTH_SHORT).show();
        startActivity(new Intent(this, LogoutActivity.class));
        overridePendingTransition(0, 0);
        finish();
    }

    @Override
    public void loginError(String msg) {
        Snackbar.make(btnLogin, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void hideKeyboard() {
        Utils.hideKeyboard(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.onDestroy();
    }
}
