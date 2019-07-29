package com.ltd.iuser.domain.dto.resource;

import com.ltd.iuser.enums.Part;
import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String code;

    private String name;

    private Part part;

    private Status status;

    private Date createTime;

    private Date updateTime;

}
