package com.ltd.iuser.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Code {

    // 格式: 3(Http状态码) + 1 (三方渠道(0代表系统本身)) + 2(具体错误)

    // 200: 成功
    SUCCESS(200000, "成功"),

    // 400: 参数不正确
    INVALID_PARAM(400001, "无效的参数"),
    ILLEGAL_PARAM(400002, "非法的参数"),
    INVALID_ID(400003, "无效的ID"),
    ILLEGAL_ID(400004, "非法的ID"),
    INVALID_EMAIL(400005, "无效的邮箱"),
    ILLEGAL_EMAIL(400006, "非法的邮箱"),
    DUPLICATE_EMAIL(400007, "重复的邮箱"),
    INVALID_MOBILE(400008, "无效的号码"),
    ILLEGAL_MOBILE(400009, "非法的号码"),
    RULE_UNIMPLEMENTED(400010, "规则未实现"),
    INVALID_MA_CODE(400011, "无效的小程序CODE"),
    SMS_CODE_INVALID(400012, "无效的验证码"),

    // 401: 身份认证与授权相关
    UNAUTHENTICATED(401000, "身份未认证"),
    INCORRECT_USERNAME_OR_PASSWORD(401001, "用户名或密码错误"),
    INSUFFICIENT_PERMISSIONS(401002, "权限不足"),
    UNAUTHORIZED(401003, "用户未授权"),
    TOKEN_VERIFY_ERROR(401005, "Token验证失败"),

    // 404: 资源未找到
    NOT_FOUND(404000, "资源未找到"),

    // 500: 服务器内部错误
    SYSTEM_ERROR(50000, "系统错误"),
    TOKEN_CREATE_ERROR(500002, "Token创建失败"),
    FETCH_FILE_ERROR(500004, "抓取图片失败"),
    UPLOAD_FILE_ERROR(500005, "抓取图片失败"),
    SMS_SEND_ERROR(500006, "短信发送失败"),
    CREATE_QR_ERROR(500007, "二维码生成失败");


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