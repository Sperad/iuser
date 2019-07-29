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

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = Constants.GeneratorStrategy.SNOWFLAKE_NAME)
    @GenericGenerator(name = Constants.GeneratorStrategy.SNOWFLAKE_NAME, strategy = Constants.GeneratorStrategy.SNOWFLAKE_REFERENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "tinyint")
    @Enumerated(EnumType.ORDINAL)
    private Part part;

    @Column(nullable = false, columnDefinition = "tinyint default 1")
    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.ENABLED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    private Role parent;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
