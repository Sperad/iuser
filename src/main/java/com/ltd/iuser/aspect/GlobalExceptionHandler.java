package com.ltd.iuser.aspect;

import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import com.ltd.iuser.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Result<Object> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception, HttpServletResponse response) {
        // TODO 日志优化
        log.warn("MethodArgumentNotValidExceptionHandler", exception);
        FieldError fieldError = exception.getBindingResult().getFieldError();
        String message = fieldError.getField() + ":" + fieldError.getDefaultMessage();
        Result<Object> result = new Result<>(Code.INVALID_PARAM);
        result.setMessage(message);
        return result;
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> exceptionHandler(Exception exception, HttpServletResponse response) {
        // TODO 日志优化
        log.warn("GlobalExceptionHandler", exception);
        Result<Object> result;
        Object[] args = null;
        if (exception instanceof BusinessException) {
            BusinessException e = (BusinessException) exception;
            Code code = Code.valueOf(e.getCode());
            args = e.getArgs();
            int httpStatus = code.getValue() / 1000;
            response.setStatus(httpStatus);
            result = new Result<>(e);
        } else {
            response.setStatus(500);
            result = new Result<>(Code.SYSTEM_ERROR);
        }

        Locale locale = LocaleContextHolder.getLocale();
        Code code = result.getCode();
        String desc = messageSource.getMessage("code." + code.name().toLowerCase(), args, code.getDesc(), locale);
        code.setDesc(desc);
        return result;
    }
}
