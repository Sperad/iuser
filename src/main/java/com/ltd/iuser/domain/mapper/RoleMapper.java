package com.ltd.iuser.domain.mapper;

import com.itian.busker.authorization.admin.domain.dto.role.RoleAdd;
import com.itian.busker.authorization.admin.domain.dto.role.RoleDTO;
import com.itian.busker.authorization.admin.domain.vo.role.RoleVO;
import com.itian.busker.authorization.common.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-04 14:06
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(target = "parent", expression = "java(roleAdd.buildParent())")
    Role from(RoleAdd roleAdd);

    RoleDTO dto(Role role);

    List<RoleDTO> dto(List<Role> roles);

    List<RoleVO> vo(List<RoleDTO> roleDTOS);

    RoleVO vo(RoleDTO roleDTO);
}
