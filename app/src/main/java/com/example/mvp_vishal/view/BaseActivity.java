package com.example.mvp_vishal.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity implements BaseView {
    // more info here : https://medium.com/@nurrohman/a-simple-android-apps-with-mvp-dagger-rxjava-and-retrofit-4edb214a66d7
    public ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeProgressDialog();
    }

    private void initializeProgressDialog() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setMessage("Processing...");
    }

    public void setMessageProgressDialog(String message) {
        mProgressDialog.setMessage(message);
    }

    public void showProgressDialog() {
        mProgressDialog.show();
    }

    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }
}
