package com.example.learnapi.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class BaseUrlInterceptor implements Interceptor {
    private String baseUrl;

    public BaseUrlInterceptor(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl newUrl = originalRequest.url().newBuilder()
                .scheme(baseUrl.startsWith("https") ? "https" : "http") // Ensure the scheme is correct
                .host(baseUrl.replaceAll("^(https?://)", ""))
                .build();
        Request newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build();
        return chain.proceed(newRequest);
    }
}
