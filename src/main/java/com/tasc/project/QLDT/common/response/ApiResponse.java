package com.tasc.project.QLDT.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;
    private List<ErrorDetail> errors;
    private Instant timestamp;

    public ApiResponse(boolean success, String message, T data, List<ErrorDetail> errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
        this.timestamp = Instant.now();
    }
    //Factory method
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true,message,data,null);
    }

    public static <T> ApiResponse<T> failure(String message, List<ErrorDetail> errors){
        return new ApiResponse<>(false,message,null,errors);
    }
}
