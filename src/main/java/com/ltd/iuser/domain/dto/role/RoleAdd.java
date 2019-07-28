package com.ltd.iuser.domain.dto.role;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itian.busker.authorization.common.entity.Role;
import com.itian.busker.common.enums.Part;
import com.itian.busker.common.pojo.json.LongJsonDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 14:08
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Getter
@Setter
public class RoleAdd implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Length(max = 50)
    private String code;

    @Length(max = 50)
    private String name;

    @NotNull
    private Part part;

    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long parentId;

    public Role buildParent() {
        if (null == parentId) {
            return null;
        }
        Role role = new Role();
        role.setId(parentId);
        return role;
    }

}
