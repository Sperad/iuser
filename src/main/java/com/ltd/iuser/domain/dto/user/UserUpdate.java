package com.ltd.iuser.domain.dto.user;

import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserUpdate implements Serializable {
    private static final long serialVersionUID = 1L;

    private Status status;
}
