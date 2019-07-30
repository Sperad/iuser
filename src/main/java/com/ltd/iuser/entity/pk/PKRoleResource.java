package com.ltd.iuser.entity.pk;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PKRoleResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private Long resourceId;

}