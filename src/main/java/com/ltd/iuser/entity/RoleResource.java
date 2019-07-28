package com.ltd.iuser.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class RoleResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @MapsId("roleId")
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @MapsId("resourceId")
    @ManyToOne
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;


    @Column(nullable = false, columnDefinition = "tinyint default 0")
    private Integer permission = 0;
}
