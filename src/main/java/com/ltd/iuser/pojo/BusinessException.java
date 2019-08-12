package com.ltd.iuser.pojo;


import com.ltd.iuser.enums.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private Integer code;
    private String detail;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public BusinessException(Code code) {
        super(code.name());
        this.code = code.getValue();
    }

    public BusinessException(Code code, String detail) {
        super(code.name());
        this.code = code.getValue();
        this.detail = detail;
    }

}
