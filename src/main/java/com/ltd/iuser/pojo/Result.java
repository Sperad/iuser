package com.ltd.iuser.pojo;

import com.ltd.iuser.enums.Code;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Code code = Code.SUCCESS;

    private T data;

    private String message;

    private Date timestamp = new Date();

    public Result() {
    }

    public static <T> Result<T> data(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }

    public Result(Code code) {
        this.code = code;
    }

    public Result(Code code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Code code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public Result(BusinessException e) {
        this.code = Code.valueOf(e.getCode());
    }

    public Result(BusinessException e, String message) {
        this.code = Code.valueOf(e.getCode());
        this.message = message;
    }

    public Result(BusinessException e, T data, String message) {
        this.code = Code.valueOf(e.getCode());
        this.data = data;
        this.message = message;
    }

}
