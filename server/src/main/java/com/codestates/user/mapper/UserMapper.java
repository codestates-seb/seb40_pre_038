package com.codestates.user.mapper;

import com.codestates.user.dto.UserDto;
import com.codestates.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User userPostToUser(UserDto.Post requestBody);
    UserDto.Response userToUserResponseDto(User user);

    User userPatchDtoToUser(UserDto.Patch requestBody);

    List<UserDto.Response> userToUserResponses(List<User> users);
}
