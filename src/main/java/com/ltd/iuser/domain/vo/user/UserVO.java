package com.ltd.iuser.domain.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ltd.iuser.enums.Status;
import com.ltd.iuser.pojo.json.LongJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    private Long id;

    private String name;

    private String email;

    private Status status;

    private Date createTime;

    private Date updateTime;
}
