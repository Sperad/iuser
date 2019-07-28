package com.ltd.iuser.utils;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class ObjectUtil {

    public static String[] getNullPropertyNames(Object obj, String... ignoreProperties) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        if (ArrayUtils.isEmpty(pds)) {
            return new String[]{};
        }

        Set<String> propertys = Arrays.stream(pds)
                .filter(pd -> (null == beanWrapper.getPropertyValue(pd.getName())))
                .map(pd -> pd.getName())
                .collect(Collectors.toSet());
        propertys.addAll(Arrays.asList(ignoreProperties));
        return propertys.toArray(new String[propertys.size()]);
    }

}
