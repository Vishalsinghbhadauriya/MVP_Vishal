package com.example.mvp_vishal.networking;

public class ErrorHandler {
    public static final String RESULT_OK = "200";

    public static boolean isError(String statusCode) {
        return statusCode.equalsIgnoreCase(RESULT_OK);
    }
}
