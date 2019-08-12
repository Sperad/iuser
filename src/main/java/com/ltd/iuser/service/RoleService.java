package com.ltd.iuser.service;

import com.ltd.iuser.domain.dto.role.RoleAdd;
import com.ltd.iuser.domain.dto.role.RoleDTO;
import com.ltd.iuser.domain.dto.role.RoleUpdate;
import com.ltd.iuser.domain.mapper.RoleMapper;
import com.ltd.iuser.entity.Role;
import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import com.ltd.iuser.repository.RoleRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private RoleMapper roleMapper;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Role findOne(long id) {
        if (1L > id) {
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }
        Role role = roleRepository.findById(id).get();

        if (null == role) {
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }
        return role;
    }

    public RoleDTO add(RoleAdd roleAdd) {
        Role role = roleMapper.from(roleAdd);
        roleRepository.save(role);
        RoleDTO roleDTO = roleMapper.dto(role);
        return roleDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public List<RoleDTO> tree() {
        List<Role> roles = roleRepository.findAllByParentIsNull();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        roles.forEach(role -> {
            RoleDTO root = roleMapper.dto(role);
            List<Role> childrens = roleRepository.findAllByParent(role);
            root.setChildrens(roleMapper.dto(childrens));
            roleDTOS.add(root);
        });
        return roleDTOS;
    }

    public RoleDTO update(long id, RoleUpdate roleUpdate) {
        if (1L > id) {
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }
        Role role = findOne(id);
        //roleRepository.save(role);
        RoleDTO roleDTO = roleMapper.dto(role);
        return roleDTO;
    }

    public void remove(long id) {
        if (1L > id) {
            throw new BusinessException(Code.SUCCESS, String.format("id = %d", id));
        }
        Role role = findOne(id);
        roleRepository.delete(role);
    }
}
