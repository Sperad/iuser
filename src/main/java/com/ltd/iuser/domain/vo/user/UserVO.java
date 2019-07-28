package com.ltd.iuser.domain.vo.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itian.busker.common.enums.Status;
import com.itian.busker.common.pojo.json.LongJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 23:19
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
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
