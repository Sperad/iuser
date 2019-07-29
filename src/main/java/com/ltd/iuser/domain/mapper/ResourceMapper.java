package com.ltd.iuser.domain.mapper;

import com.ltd.iuser.domain.dto.resource.ResourceAdd;
import com.ltd.iuser.domain.dto.resource.ResourceDTO;
import com.ltd.iuser.domain.vo.Resource.ResourceVO;
import com.ltd.iuser.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ResourceMapper {

    Resource build(Long id);

    ResourceVO vo(ResourceDTO resourceDTO);

    Resource from(ResourceAdd resourceAdd);

    ResourceDTO dto(Resource resource);
}
