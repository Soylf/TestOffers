package com.example.demo.model.mapper;

import com.example.demo.model.EntityUser;
import com.example.demo.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    UserDto toDto(EntityUser user);
    EntityUser fromDto(UserDto userDto);
}
