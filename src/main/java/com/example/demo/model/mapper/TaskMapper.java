package com.example.demo.model.mapper;

import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);
    Task fromDto(TaskDto taskDto);
}