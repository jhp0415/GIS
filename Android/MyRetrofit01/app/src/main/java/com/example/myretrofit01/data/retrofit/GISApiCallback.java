package com.example.myretrofit01.data.retrofit;

public interface GISApiCallback<T> {
    void onSuccess(int code, T receivedData);
    void onFailure(int code);
    void onError(Throwable t);
}
