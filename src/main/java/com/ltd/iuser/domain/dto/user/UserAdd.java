package com.ltd.iuser.domain.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-05 14:02
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Getter
@Setter
public class UserAdd implements Serializable {
    private static final long serialVersionUID = 1L;

    @Length(max = 50)
    @NotBlank
    private String name;

    @Length(max = 50)
    @NotBlank
    private String email;
}
