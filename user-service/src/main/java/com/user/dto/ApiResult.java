package com.user.dto;

import com.user.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResult<T> {

    private int code;
    private String message;
    private T data;

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public ApiResult(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static ApiResult success() {
        return new ApiResult(ResultCode.SUCCESS, null);
    }

    public static <T> ApiResult<T> success(T data) {
        return new ApiResult(ResultCode.SUCCESS, data);
    }

    public static ApiResult fail() {
        return new ApiResult(ResultCode.FAIL);
    }

    public static ApiResult fail(int code, String message) {
        return new ApiResult(code, message, null);
    }

    public static ApiResult fail(String message) {

        return new ApiResult(-1, message, null);
    }

    public static ApiResult fail(ResultCode resultCode) {
        return new ApiResult(resultCode.getCode(), resultCode.getMessage());
    }

}
