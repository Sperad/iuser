package com.ltd.iuser.domain.dto.role;

import com.itian.busker.common.enums.Part;
import com.itian.busker.common.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 13:58
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Getter
@Setter
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String name;

    private Part part;

    private Status status;

    private Date createTime;

    private Date updateTime;

    private List<RoleDTO> childrens;
}
