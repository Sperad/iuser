package com.ltd.iuser.domain.dto.role;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ltd.iuser.entity.Role;
import com.ltd.iuser.enums.Part;
import com.ltd.iuser.pojo.json.LongJsonDeserializer;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
