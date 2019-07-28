package com.ltd.iuser.pojo;


import com.ltd.iuser.enums.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private String code;

    private Object[] args;

    private String detail;

    public BusinessException(String code) {
        super(code);
        this.code = code;
    }

    public BusinessException(String code, Object[] args) {
        super(code);
        this.code = code;
        this.args = args;
    }

    public BusinessException(String code, Throwable cause) {
        super(code, cause);
        this.code = code;
    }

    public BusinessException(String code, Object[] args, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.args = args;
    }

    public BusinessException(String code, String detail) {
        super(code);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(String code, Object[] args, String detail) {
        super(code);
        this.code = code;
        this.args = args;
        this.detail = detail;
    }

    public BusinessException(String code, String detail, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.detail = detail;
    }

    public BusinessException(String code, Object[] args, String detail, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.args = args;
        this.detail = detail;
    }

    public BusinessException(Code code) {
        super(code.name());
        this.code = code.name();
    }

    public BusinessException(Code code, Object[] args) {
        super(code.name());
        this.code = code.name();
        this.args = args;
    }

    public BusinessException(Code code, Throwable cause) {
        super(code.name(), cause);
        this.code = code.name();
    }

    public BusinessException(Code code, Object[] args, Throwable cause) {
        super(code.name(), cause);
        this.code = code.name();
        this.args = args;
    }
    public BusinessException(Code code, String detail) {
        super(code.name());
        this.code = code.name();
        this.detail = detail;
    }

    public BusinessException(Code code, Object[] args, String detail) {
        super(code.name());
        this.code = code.name();
        this.args = args;
        this.detail = detail;
    }

    public BusinessException(Code code, String detail, Throwable cause) {
        super(code.name(), cause);
        this.code = code.name();
        this.detail = detail;
    }

    public BusinessException(Code code, Object[] args, String detail, Throwable cause) {
        super(code.name(), cause);
        this.code = code.name();
        this.args = args;
        this.detail = detail;
    }


    public BusinessException(Result result) {
        super(result.getCode().name());
        this.code = result.getCode().name();
    }

    public BusinessException(Result result, Object[] args) {
        super(result.getCode().name());
        this.code = result.getCode().name();
        this.args = args;
    }

    public BusinessException(Result result, Throwable cause) {
        super(result.getCode().name(), cause);
        this.code = result.getCode().name();
    }

    public BusinessException(Result result, Object[] args, Throwable cause) {
        super(result.getCode().name(), cause);
        this.code = result.getCode().name();
        this.args = args;
    }

    public BusinessException(Result result, String detail) {
        super(result.getCode().name());
        this.code = result.getCode().name();
        this.detail = detail;
    }

    public BusinessException(Result result, Object[] args, String detail) {
        super(result.getCode().name());
        this.code = result.getCode().name();
        this.args = args;
        this.detail = detail;
    }

    public BusinessException(Result result, Object[] args, String detail, Throwable cause) {
        super(result.getCode().name(), cause);
        this.code = result.getCode().name();
        this.args = args;
        this.detail = detail;
    }

    public BusinessException(Result result, String detail, Throwable cause) {
        super(result.getCode().name(), cause);
        this.code = result.getCode().name();
        this.detail = detail;
    }
}
