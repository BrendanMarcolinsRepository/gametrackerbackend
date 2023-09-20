package com.api.gamerating.mappers;

import com.api.gamerating.dto.SignUpDto;
import com.api.gamerating.dto.UserDto;
import com.api.gamerating.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserDto.class, SignUpDto.class})
public interface UserMapper {

    UserDto toUserDto(User user);

    @Mapping(target = "password", ignore = true)
    User signUpToUser(SignUpDto signUp);
}
