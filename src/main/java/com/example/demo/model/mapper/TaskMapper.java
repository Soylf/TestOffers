package com.example.demo.model.mapper;

import com.example.demo.model.Task;
import com.example.demo.model.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);
    TaskDto toDto(Task task);
    Task fromDto(TaskDto taskDto);
}
