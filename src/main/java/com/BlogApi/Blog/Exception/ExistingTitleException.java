package com.BlogApi.Blog.Exception;

public class ExistingTitleException extends Exception{
    private String message;
    private String operation_name;
    private boolean success;
    public ExistingTitleException() {
        super();
    }

    public ExistingTitleException(String operation_name, String message, boolean success) {
        this.message = message;
        this.operation_name = operation_name;
        this.success = success;
    }


    public String getOperation_name() {
        return operation_name;
    }

    public void setOperation_name(String operation_name) {
        this.operation_name = operation_name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
