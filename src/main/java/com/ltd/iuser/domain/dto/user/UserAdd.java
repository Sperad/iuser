package com.ltd.iuser.domain.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
