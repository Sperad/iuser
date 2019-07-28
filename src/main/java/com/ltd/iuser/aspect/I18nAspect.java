package com.ltd.iuser.aspect;

import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.Result;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author jett.gao
 */
@Aspect
@Component
@Order
public class I18nAspect {

    @Autowired
    private MessageSource messageSource;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping ) || @annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object obj = proceedingJoinPoint.proceed();
        if (obj != null && obj.getClass() == Result.class) {
            Result result = (Result) obj;
            Locale locale = LocaleContextHolder.getLocale();
            Code code = result.getCode();
            String desc = messageSource.getMessage("code." + code.name().toLowerCase(), null, code.getDesc(), locale);
            code.setDesc(desc);
        }
        return obj;
    }
}
