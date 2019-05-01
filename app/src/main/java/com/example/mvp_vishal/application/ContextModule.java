package com.example.mvp_vishal.application;

import android.content.Context;

import com.example.mvp_vishal.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {

    private Context context;

    ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @PerApplication
    public Context providesContext() {
        return context;
    }
}