package com.ltd.iuser.service;

import com.ltd.iuser.domain.dto.resource.ResourceAdd;
import com.ltd.iuser.domain.dto.resource.ResourceDTO;
import com.ltd.iuser.domain.dto.resource.ResourceUpdate;
import com.ltd.iuser.domain.mapper.ResourceMapper;
import com.ltd.iuser.entity.Resource;
import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import com.ltd.iuser.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }

        Resource resource = resourceRepository.findById(id).get();
        if (null == resource) {
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }
        return resource;
    }

    public ResourceDTO add(ResourceAdd resourceAdd) {
        Resource resource = resourceMapper.from(resourceAdd);
        resourceRepository.save(resource);
        ResourceDTO resourceDTO = resourceMapper.dto(resource);
        return resourceDTO;
    }

    public ResourceDTO update(Long id, ResourceUpdate resourceUpdate) {
        Resource resource = findOne(id);
        System.out.println(resource);
        //resourceRepository.save(resource);
        ResourceDTO resourceDTO = resourceMapper.dto(resource);
        return resourceDTO;
    }
}
