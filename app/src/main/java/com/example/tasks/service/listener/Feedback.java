package com.example.tasks.service.listener;

public class Feedback {

    private boolean success = true;
    private String message = "";

    public Feedback() {
    }

    public Feedback(String message) {
        this.success = false;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
