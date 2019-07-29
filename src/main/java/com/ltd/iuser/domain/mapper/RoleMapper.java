package com.ltd.iuser.domain.mapper;

import com.ltd.iuser.domain.dto.role.RoleAdd;
import com.ltd.iuser.domain.dto.role.RoleDTO;
import com.ltd.iuser.domain.vo.role.RoleVO;
import com.ltd.iuser.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(target = "parent", expression = "java(roleAdd.buildParent())")
    Role from(RoleAdd roleAdd);

    RoleDTO dto(Role role);

    List<RoleDTO> dto(List<Role> roles);

    List<RoleVO> vo(List<RoleDTO> roleDTOS);

    RoleVO vo(RoleDTO roleDTO);
}
