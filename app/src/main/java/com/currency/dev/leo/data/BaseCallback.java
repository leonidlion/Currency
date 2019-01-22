package com.currency.dev.leo.data;

public interface BaseCallback<T> {
    void onSuccess(T data);
    void onError(int code, String message);
    void onFailure(Throwable throwable);
}
