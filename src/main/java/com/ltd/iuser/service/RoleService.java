package com.ltd.iuser.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

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
            throw new BuskerException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }
        Role role = roleRepository.findOne(id);
        return role;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Role getOne(long id) {
        Role role = findOne(id);
        if (null == role) {
            throw new BuskerException(Code.INVALID_ID, String.format("id = %d", id));
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
            throw new BuskerException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }
        Role role = getOne(id);
        BeanUtils.copyProperties(roleUpdate, role, ObjectUtil.getNullPropertyNames(roleUpdate));
        roleRepository.save(role);
        RoleDTO roleDTO = roleMapper.dto(role);
        return roleDTO;
    }

    public void remove(long id) {
        if (1L > id) {
            throw new BuskerException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }
        Role role = getOne(id);
        roleRepository.delete(role);
    }
}
