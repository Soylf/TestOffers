package com.example.demo.repository.model.mapper;

import com.example.demo.repository.model.Task;
import com.example.demo.repository.model.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);
    TaskDto toDto(Task task);
    Task fromDto(TaskDto taskDto);
}
