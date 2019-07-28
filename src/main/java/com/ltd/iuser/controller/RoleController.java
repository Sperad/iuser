package com.ltd.iuser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author jett.gao
 * @description TODO
 * @date 2019-06-05 16:39
 * @copyright: 2019 www.itian365.com Inc. All rights reserved.
 */
@RequestMapping("/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @PostMapping
    public Result<RoleVO> add(@Valid @RequestBody RoleAdd roleAdd) {
        RoleDTO roleDTO = roleService.add(roleAdd);
        RoleVO roleVO = roleMapper.vo(roleDTO);
        return Result.data(roleVO);
    }

    @PutMapping("/{id}")
    public Result<RoleVO> update(@PathVariable("id") Long id, @Valid @RequestBody RoleUpdate roleUpdate) {
        RoleDTO roleDTO = roleService.update(id, roleUpdate);
        RoleVO roleVO = roleMapper.vo(roleDTO);
        return Result.data(roleVO);
    }

    @DeleteMapping("/{id}")
    public Result<Boolean> remove(@PathVariable("id") Long id) {
        roleService.remove(id);
        return Result.data(true);
    }

    @GetMapping("/tree")
    public Result<List<RoleVO>> tree() {
        List<RoleDTO> roleDTOS = roleService.tree();
        List<RoleVO> roleVOS = roleMapper.vo(roleDTOS);
        return Result.data(roleVOS);
    }

}
