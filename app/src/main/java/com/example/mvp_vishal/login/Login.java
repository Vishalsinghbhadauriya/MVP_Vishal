package com.example.mvp_vishal.login;

import com.google.gson.annotations.SerializedName;


public class Login {

    @SerializedName("token")
    public String token;
    @SerializedName("is_otp_verified")
    public String isOtpVerfied;
}
