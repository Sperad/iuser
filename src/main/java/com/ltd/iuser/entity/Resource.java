package com.ltd.iuser.entity;

import com.ltd.iuser.enums.Constants;
import com.ltd.iuser.enums.Part;
import com.ltd.iuser.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Embeddable
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING, length = 10)
@DiscriminatorValue("RESOURCE")
@EntityListeners(AuditingEntityListener.class)
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(nullable = false, length = 100)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "tinyint")
    @Enumerated(EnumType.ORDINAL)
    private Part part;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ENABLED;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;

    @OneToMany(mappedBy = "role")
    private Set<RoleResource> roleResources = new HashSet<>();
}
