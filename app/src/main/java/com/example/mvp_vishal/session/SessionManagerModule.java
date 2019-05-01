package com.example.mvp_vishal.session;

import android.content.Context;

import com.example.mvp_vishal.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class SessionManagerModule {

    @Provides
    @PerApplication
    SessionManager provideSessionManger(Context context) {
        return new SessionManager(context);
    }
}
