package com.ltd.iuser.domain.vo.Resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.itian.busker.common.enums.Part;
import com.itian.busker.common.pojo.json.LongJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 16:40
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
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
