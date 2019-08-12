package com.ltd.iuser.pojo;

import com.ltd.iuser.enums.Code;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer code;

    private T data;

    private String message;

    private Timestamp timestamp = new Timestamp(new Date().getTime());

    public Result() {
    }

    public static <T> Result<T> data(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        return result;
    }
    public Result(Code code) {
        this.code = code.getValue();
        this.message = code.getDesc();
    }

    public Result(Code code, String message) {
        this.code = code.getValue();
        if (message.isEmpty()) {
          this.message = code.getDesc();
          return;
        }
        this.message = message;
    }

    public Result(Code code, T data, String message) {
        this.code = code.getValue();
        this.data = data;
        this.message = message;
    }

    public Result(BusinessException e) {
        this.code = e.getCode();
    }

    public Result(BusinessException e, String message) {
        this.code = e.getCode();
        this.message = message;
    }

    public Result(BusinessException e, T data, String message) {
        this.code = e.getCode();
        this.data = data;
        this.message = message;
    }

}
