package com.ltd.iuser.domain.dto.resource;

import com.ltd.iuser.enums.Part;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ResourceAdd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 100)
    @NotBlank
    private String code;

    @Length(max = 50)
    @NotBlank
    private String name;

    @NotNull
    private Part part;

}
