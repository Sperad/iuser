package com.ltd.iuser.domain.dto.user;

import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Status status;

    private Date createTime;

    private Date updateTime;

    private String unionId;
}
