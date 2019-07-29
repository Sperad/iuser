package com.ltd.iuser.controller;

import com.ltd.iuser.domain.dto.role.RoleAdd;
import com.ltd.iuser.domain.dto.role.RoleDTO;
import com.ltd.iuser.domain.dto.role.RoleUpdate;
import com.ltd.iuser.domain.mapper.RoleMapper;
import com.ltd.iuser.domain.vo.role.RoleVO;
import com.ltd.iuser.pojo.Result;
import com.ltd.iuser.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
