package com.ltd.iuser.utils;

import com.itian.busker.common.enums.Permission;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class PermissionUtil {

    public static boolean permmit(int value, Permission permission) {
        return 0 != (value & permission.getValue());
    }


    public static int init(Permission... permissions) {
        return add(0, permissions);
    }

    public static int add(int value, Permission... permissions) {
        if (ArrayUtils.isEmpty(permissions)) {
            return value;
        }
        for (Permission permission : permissions) {
            value |= permission.getValue();
        }
        return value;
    }

    public static int delete(int value, Permission... permissions) {
        if (ArrayUtils.isEmpty(permissions)) {
            return value;
        }
        for (Permission permission : permissions) {
            value &= (~permission.getValue());
        }
        return value;
    }

    public static Set<Permission> parse(int value) {
        Set<Permission> permissionSet = new HashSet<>();
        Arrays.asList(Permission.values()).stream().forEach(permission -> {
            if (permmit(value, permission)) {
                permissionSet.add(permission);
            }
        });
        return permissionSet;
    }
}
