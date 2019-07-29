package com.ltd.iuser.service;

import com.ltd.iuser.domain.dto.resource.ResourceAdd;
import com.ltd.iuser.domain.dto.resource.ResourceDTO;
import com.ltd.iuser.domain.dto.resource.ResourceUpdate;
import com.ltd.iuser.domain.mapper.ResourceMapper;
import com.ltd.iuser.entity.Resource;
import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import com.ltd.iuser.pojo.page.PageQuery;
import com.ltd.iuser.pojo.page.PageUtil;
import com.ltd.iuser.repository.ResourceRepository;
import com.ltd.iuser.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor = Exception.class)
@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private ResourceMapper resourceMapper;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Resource findOne(long id) {
        if (1L > id) {
            throw new BusinessException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }

        Resource resource = resourceRepository.findById(id).get();
        if (null == resource) {
            throw new BusinessException(Code.INVALID_ID, String.format("id = %d", id));
        }
        return resource;
    }

    public ResourceDTO add(ResourceAdd resourceAdd) {
        Resource resource = resourceMapper.from(resourceAdd);
        resourceRepository.save(resource);
        ResourceDTO resourceDTO = resourceMapper.dto(resource);
        return resourceDTO;
    }

    public Page<ResourceDTO> search(PageQuery pageQuery) {
        Page<Resource> administratorPage = resourceRepository.findAll(PageUtil.getSpecification(pageQuery), PageUtil.getPageable(pageQuery));
        Page<ResourceDTO> administratorDTOPage = administratorPage.map(resourceMapper::dto);
        return administratorDTOPage;
    }

    public ResourceDTO update(Long id, ResourceUpdate resourceUpdate) {
        Resource resource = findOne(id);
        BeanUtils.copyProperties(resourceUpdate, resource, ObjectUtil.getNullPropertyNames(resourceUpdate));
        resourceRepository.save(resource);
        ResourceDTO resourceDTO = resourceMapper.dto(resource);
        return resourceDTO;
    }
}
