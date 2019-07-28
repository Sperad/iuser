package com.ltd.iuser.domain.mapper;

import com.ltd.iuser.domain.dto.user.UserAdd;
import com.ltd.iuser.domain.dto.user.UserDTO;
import com.ltd.iuser.domain.vo.user.UserVO;
import com.ltd.iuser.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User build(Long id);

    User from(UserAdd userAdd);

    UserDTO dto(User user);

    UserVO vo(UserDTO userDTO);
}
