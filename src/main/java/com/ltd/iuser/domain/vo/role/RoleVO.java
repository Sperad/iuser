package com.ltd.iuser.domain.vo.role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ltd.iuser.enums.Part;
import com.ltd.iuser.enums.Status;
import com.ltd.iuser.pojo.json.LongJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RoleVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    private String code;

    private String name;

    private Part part;

    private Status status;

    private List<RoleVO> childrens;

    private Date createTime;

    private Date updateTime;

}
