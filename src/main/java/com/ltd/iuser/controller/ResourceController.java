package com.ltd.iuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/search")
    public Result<Page<ResourceVO>> search(@Valid @RequestBody PageQuery pageQuery) {
        Page<ResourceDTO> resourceDTOPage = resourceService.search(pageQuery);
        Page<ResourceVO> resourceVOPage = resourceDTOPage.map(resourceMapper::vo);
        return Result.data(resourceVOPage);
    }

    @PutMapping("/{id}")
    public Result<ResourceVO> update(@PathVariable("id") Long id, @Valid @RequestBody ResourceUpdate resourceUpdate) {
        ResourceDTO resourceDTO = resourceService.update(id, resourceUpdate);
        ResourceVO resourceVO   = resourceMapper.vo(resourceDTO);
        return Result.data(resourceVO);
    }

}
