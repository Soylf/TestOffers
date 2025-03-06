package com.example.demo.model.mapper;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserDto toDto(User user);

    @Mapping(target = "password", ignore = true)
    User fromDto(UserDto userDto);
}
