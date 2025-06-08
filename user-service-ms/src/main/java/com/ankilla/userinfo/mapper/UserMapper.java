package com.ankilla.userinfo.mapper;

import com.ankilla.userinfo.dto.UserDTO;
import com.ankilla.userinfo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

//    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserDTOToUser (UserDTO userDTO);
    UserDTO mapUserToUserDTO(User user);
}
