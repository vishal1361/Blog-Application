package com.BlogApi.Blog.Exception;

public class ApiResponse {
    private String status;
    private String error;
    private boolean success;
    private String operation_name;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOperation_name() {
        return operation_name;
    }

    public void setOperation_name(String operation_name) {
        this.operation_name = operation_name;
    }

    public ApiResponse(String status, String operation_name, String error, boolean success) {
        this.status = status;
        this.error = error;
        this.success = success;
        this.operation_name = operation_name;
    }

    public ApiResponse() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
