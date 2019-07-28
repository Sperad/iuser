package com.ltd.iuser.repository;

import com.itian.busker.authorization.common.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 14:07
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAllByParentIsNull();

    List<Role> findAllByParent(Role role);
}
