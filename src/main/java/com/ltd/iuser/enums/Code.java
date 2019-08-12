package com.ltd.iuser.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Code {
    // 格式: 产品编码(4位) + 功能模块编码(3位) + 业务错误码(3位)

    SUCCESS(100010010, "success");

    private Integer value;
    private String desc;

    Code(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static Code getCode(Integer value) {
        return Arrays.asList(Code.values()).stream()
                .filter(code -> code.getValue().equals(value))
                .findFirst().get();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}