package com.example.mvp_vishal.session;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mvp_vishal.login.Login;
import com.example.mvp_vishal.utils.Constants;

public class SessionManager {
    private static final String PREFS_API_TOKEN = "api_token";

    private SharedPreferences mPrefs;

    public SessionManager(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveLogin(Login login) {
        SharedPreferences.Editor e = mPrefs.edit();
        e.putString(PREFS_API_TOKEN, login.token);
        e.apply();
    }

    public void logout() {
        mPrefs.edit().clear().apply();
    }

    public boolean isLoggedIn() {
        return !mPrefs.getString(PREFS_API_TOKEN, "").equals("");
    }

    public String getToken() {
        return mPrefs.getString(PREFS_API_TOKEN, Constants.HEADER_DEFAULT_VALUE);
    }
}
