package com.ltd.iuser.service;

import com.itian.busker.authorization.admin.domain.dto.resource.ResourceAdd;
import com.itian.busker.authorization.admin.domain.dto.resource.ResourceDTO;
import com.itian.busker.authorization.admin.domain.dto.resource.ResourceUpdate;
import com.itian.busker.authorization.admin.domain.mapper.ResourceMapper;
import com.itian.busker.authorization.admin.repository.ResourceRepository;
import com.itian.busker.authorization.common.entity.Resource;
import com.itian.busker.common.Code;
import com.itian.busker.common.pojo.BuskerException;
import com.itian.busker.common.pojo.page.PageQuery;
import com.itian.busker.common.pojo.page.PageUtil;
import com.itian.busker.common.utils.ObjectUtil;
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
    public Resource getOne(long id) {
        Resource resource = findOne(id);
        if (null == resource) {
            throw new BuskerException(Code.INVALID_ID, String.format("id = %d", id));
        }
        return resource;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Resource findOne(long id) {
        if (1L > id) {
            throw new BuskerException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }
        Resource resource = resourceRepository.findOne(id);
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
        Resource resource = getOne(id);
        BeanUtils.copyProperties(resourceUpdate, resource, ObjectUtil.getNullPropertyNames(resourceUpdate));
        resourceRepository.save(resource);
        ResourceDTO resourceDTO = resourceMapper.dto(resource);
        return resourceDTO;
    }
}
