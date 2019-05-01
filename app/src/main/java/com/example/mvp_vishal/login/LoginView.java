package com.example.mvp_vishal.login;


import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseView;

public interface LoginView extends BaseView {
    public void showProgress();

    public void hideProgress();

    public void emailError();

    public void passwordError();

    public void loginSuccess(Login login, SessionManager sessionManager);

    public void loginError(String msg);

    public void hideKeyboard();
}
