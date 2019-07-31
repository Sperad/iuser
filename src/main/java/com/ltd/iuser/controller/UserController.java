package com.ltd.iuser.controller;

import com.ltd.iuser.domain.dto.user.UserDTO;
import com.ltd.iuser.domain.dto.user.UserUpdate;
import com.ltd.iuser.domain.mapper.UserMapper;
import com.ltd.iuser.domain.vo.user.UserVO;
import com.ltd.iuser.pojo.Result;
import com.ltd.iuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @PutMapping("/{id}")
    public Result<UserVO> update(@PathVariable("id") Long id, @Valid @RequestBody UserUpdate UserUpdate) {
        UserDTO userDTO = userService.update(id, UserUpdate);
        UserVO UserVO = userMapper.vo(userDTO);
        return Result.data(UserVO);
    }

}
