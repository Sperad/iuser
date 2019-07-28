package com.ltd.iuser.domain.dto.role;

import com.itian.busker.common.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-05 17:04
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Getter
@Setter
public class RoleUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Status status;

    private String name;

}
