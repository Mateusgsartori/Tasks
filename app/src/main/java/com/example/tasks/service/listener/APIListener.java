package com.example.tasks.service.listener;

public interface APIListener <T> {
    void onSuccess(T result);
    void onFailure(String message);
}
