package com.example.demo.model.mapper;

import com.example.demo.model.Task;
import com.example.demo.model.dto.TaskDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "author.id", target = "authorId")
    TaskDto toDto(Task task);

    @Mapping(source = "authorId", target = "author.id")
    Task fromDto(TaskDto taskDto);
}
