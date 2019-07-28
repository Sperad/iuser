package com.ltd.iuser.domain.mapper;

import com.itian.busker.authorization.admin.domain.dto.resource.ResourceAdd;
import com.itian.busker.authorization.admin.domain.dto.resource.ResourceDTO;
import com.itian.busker.authorization.admin.domain.vo.Resource.ResourceVO;
import com.itian.busker.authorization.common.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    Resource build(Long id);

    ResourceVO vo(ResourceDTO resourceDTO);

    Resource from(ResourceAdd resourceAdd);

    ResourceDTO dto(Resource resource);
}
