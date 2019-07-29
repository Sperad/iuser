package com.ltd.iuser.service;

import com.ltd.iuser.domain.dto.user.UserAdd;
import com.ltd.iuser.domain.dto.user.UserDTO;
import com.ltd.iuser.domain.dto.user.UserUpdate;
import com.ltd.iuser.domain.mapper.UserMapper;
import com.ltd.iuser.entity.User;
import com.ltd.iuser.enums.Code;
import com.ltd.iuser.pojo.BusinessException;
import com.ltd.iuser.pojo.page.PageQuery;
import com.ltd.iuser.pojo.page.PageUtil;
import com.ltd.iuser.repository.UserRepository;
import com.ltd.iuser.utils.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public User findOne(long id) {
        if (1L > id) {
            throw new BusinessException(Code.ILLEGAL_ID, String.format("id = %d", id));
        }

        User user = userRepository.findById(id).get();
        if (null == user) {
            throw new BusinessException(Code.INVALID_ID, String.format("id = %d", id));
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserDTO findById(long id) {
        User user = findOne(id);
        UserDTO userDTO = userMapper.dto(user);
        return userDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public UserDTO getById(long id) {
        User user = findOne(id);
        UserDTO userDTO = userMapper.dto(user);
        return userDTO;
    }

    public UserDTO add(UserAdd userAdd) {
        if (userRepository.existsByEmail(userAdd.getEmail())) {
            throw new BusinessException(Code.DUPLICATE_EMAIL, String.format("email = %s", userAdd.getEmail()));
        }
        User user = userMapper.from(userAdd);
        user.setPassword(new BCryptPasswordEncoder().encode("88888888"));
        userRepository.save(user);
        UserDTO userDTO = userMapper.dto(user);
        return userDTO;
    }

    public UserDTO update(long id, UserUpdate userUpdate) {
        User user = findOne(id);
        BeanUtils.copyProperties(userUpdate, user, ObjectUtil.getNullPropertyNames(userUpdate));
        userRepository.save(user);
        UserDTO userDTO = userMapper.dto(user);
        return userDTO;
    }

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Page<UserDTO> search(PageQuery pageQuery) {
        Page<User> userPage = userRepository.findAll(PageUtil.getSpecification(pageQuery), PageUtil.getPageable(pageQuery));
        Page<UserDTO> userDTOPage = userPage.map(userMapper::dto);
        return userDTOPage;
    }
}
