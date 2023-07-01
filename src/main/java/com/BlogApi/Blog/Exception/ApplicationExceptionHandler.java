package com.BlogApi.Blog.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExistingTitleException.class)
    public ApiResponse handleNoContentFoundException(ExistingTitleException ex) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getOperation_name(), ex.getMessage(), ex.isSuccess());
        return apiResponse;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BlogIdNotFoundException.class)
    public ApiResponse handleInvalidArgumentException(BlogIdNotFoundException ex) {
        ApiResponse apiResponse = new ApiResponse(HttpStatus.BAD_REQUEST.getReasonPhrase(), ex.getOperation_Name(), ex.getMessage(), ex.isSuccess());
        return apiResponse;
    }
}
