package com.example.mvp_vishal.application;


import com.google.gson.annotations.SerializedName;

public class AppResponse<T> {
    @SerializedName("status")
    public boolean status;

    @SerializedName("error")
    public String error;

    @SerializedName("message")
    public String message;

    @SerializedName("data")
    public T data;
}
