package com.example.demo.model.mapper;

import com.example.demo.model.Comment;
import com.example.demo.model.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);
    CommentDto toDto(Comment comment);
    Comment fromDto(CommentDto commentDto);
}
