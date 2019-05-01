package com.example.mvp_vishal.logout;


import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.view.BaseView;

public interface LogoutView extends BaseView {
    public void showProgress();

    public void hideProgress();

    public void onLogoutError(String error);

    public void onLogoutSuccess(SessionManager sessionManager);
}
