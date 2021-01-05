package com.user.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResult<T> {

    private static final int SUCCESS_CODE = 0;
    private static final int FAIL_CODE = -1;


    private int code;
    private String message;
    private T data;

    public static <T> RpcResult<T> success(T data) {
        RpcResult result = new RpcResult();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static <T> RpcResult<T> fail(int code, String message) {
        RpcResult result = new RpcResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> RpcResult<T> fail(String message) {
        RpcResult result = new RpcResult();
        result.setCode(FAIL_CODE);
        result.setMessage(message);
        return result;
    }

}
