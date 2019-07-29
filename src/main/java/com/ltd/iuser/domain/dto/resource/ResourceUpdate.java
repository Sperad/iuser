package com.ltd.iuser.domain.dto.resource;

import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResourceUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Status status;

    private String name;
}
