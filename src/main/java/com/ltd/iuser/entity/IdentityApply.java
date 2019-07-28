package com.ltd.iuser.entity;

import com.itian.busker.common.Constants;
import com.itian.busker.common.enums.Audit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-14 10:28
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class IdentityApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = Constants.GeneratorStrategy.SNOWFLAKE_NAME)
    @GenericGenerator(name = Constants.GeneratorStrategy.SNOWFLAKE_NAME, strategy = Constants.GeneratorStrategy.SNOWFLAKE_REFERENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 20)
    private String mobile;

    @ManyToOne
    @JoinColumn
    private Profession profession;

    @Column(length = 20)
    private String newProfession;

    @Column(nullable = false, length = 50)
    private String cover;

    @Column(length = 50)
    private String opus;

    @Column(nullable = false, columnDefinition = "tinyint default 0")
    @Enumerated(EnumType.ORDINAL)
    private Audit audit = Audit.REVIEWING;

    @Column(length = 100)
    private String rejectReason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false, nullable = false, unique = true)
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(nullable = false)
    private Date updateTime;
}
