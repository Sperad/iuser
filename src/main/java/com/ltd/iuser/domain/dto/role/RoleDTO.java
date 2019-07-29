package com.ltd.iuser.domain.dto.role;

import com.ltd.iuser.enums.Part;
import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
