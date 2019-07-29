package com.ltd.iuser.domain.vo.Resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ltd.iuser.enums.Part;
import com.ltd.iuser.pojo.json.LongJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ResourceVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    private String code;

    private String name;

    private Part part;

    private Date createTime;

    private Date updateTime;
}
