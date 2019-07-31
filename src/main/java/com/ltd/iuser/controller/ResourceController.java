package com.ltd.iuser.controller;

import com.ltd.iuser.domain.dto.resource.ResourceAdd;
import com.ltd.iuser.domain.dto.resource.ResourceDTO;
import com.ltd.iuser.domain.dto.resource.ResourceUpdate;
import com.ltd.iuser.domain.mapper.ResourceMapper;
import com.ltd.iuser.domain.vo.Resource.ResourceVO;
import com.ltd.iuser.pojo.Result;
import com.ltd.iuser.pojo.page.PageQuery;
import com.ltd.iuser.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceService resourceService;

    @PostMapping
    public Result<ResourceVO> add(@Valid @RequestBody ResourceAdd resourceAdd) {
        ResourceDTO resourceDTO = resourceService.add(resourceAdd);
        ResourceVO resourceVO  = resourceMapper.vo(resourceDTO);
        return  Result.data(resourceVO);
    }

    @PutMapping("/{id}")
    public Result<ResourceVO> update(@PathVariable("id") Long id, @Valid @RequestBody ResourceUpdate resourceUpdate) {
        ResourceDTO resourceDTO = resourceService.update(id, resourceUpdate);
        ResourceVO resourceVO   = resourceMapper.vo(resourceDTO);
        return Result.data(resourceVO);
    }

}
