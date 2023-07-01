package com.BlogApi.Blog.Exception;

public class ResourceNotFoundException extends Exception{
    private String message;
    private String Operation_Name;
    private boolean success;


    public ResourceNotFoundException(String Operation_Name, boolean success, String message) {
        super(message);
        this.message = message;
        this.Operation_Name = Operation_Name;
        this.success = success;
    }

    public ResourceNotFoundException() {
        super();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getOperation_Name() {
        return Operation_Name;
    }

    public void setOperation_Name(String operation_Name) {
        Operation_Name = operation_Name;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
