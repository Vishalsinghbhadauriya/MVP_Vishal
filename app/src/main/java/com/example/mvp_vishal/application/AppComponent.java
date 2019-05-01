package com.example.mvp_vishal.application;

import com.example.mvp_vishal.ActivityComponent;
import com.example.mvp_vishal.ActivityModule;
import com.example.mvp_vishal.networking.NetworkModule;
import com.example.mvp_vishal.networking.NetworkService;
import com.example.mvp_vishal.scopes.PerApplication;
import com.example.mvp_vishal.session.SessionManager;
import com.example.mvp_vishal.session.SessionManagerModule;

import dagger.Component;

@PerApplication
@Component(modules = {NetworkModule.class, SessionManagerModule.class, ContextModule.class})
public interface AppComponent {

    NetworkService netWorkService();

    SessionManager sessionManager();

    ActivityComponent activityComponent(ActivityModule activityModule);

    }
