package com.example.demo.repository.model.mapper;

import com.example.demo.repository.model.EntityUser;
import com.example.demo.repository.model.dto.EntityUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    EntityUserDto toDto(EntityUser user);
    EntityUser fromDto(EntityUserDto userDto);
}
