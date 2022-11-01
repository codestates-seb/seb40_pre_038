package com.codestates.exception.user.mapper;

import com.codestates.exception.user.dto.UserDto;
import com.codestates.exception.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostToUser(UserDto.Post requestBody);
    UserDto.Response userToUserResponseDto(User user);
}
