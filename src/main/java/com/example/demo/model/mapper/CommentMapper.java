package com.example.demo.model.mapper;

import com.example.demo.model.Comment;
import com.example.demo.model.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "task.id", target = "task.id")
    CommentDto toDto(Comment comment);

    @Mapping(source = "user.id", target = "user.id")
    @Mapping(source = "task.id", target = "task.id")
    Comment fromDto(CommentDto commentDto);
}
