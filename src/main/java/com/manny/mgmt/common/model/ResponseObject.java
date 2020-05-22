package com.manny.mgmt.common.model;

public class ResponseObject {
    private boolean isSuccess = false;
    private String message = "";

    public ResponseObject() {}

    public ResponseObject(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
