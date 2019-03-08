package com.example.myretrotif02.retrofit;

public interface GISApiCallback<T> {
    void onSuccess(int code, T receivedData);
    void onFailure(int code);
    void onError(Throwable t);
}
