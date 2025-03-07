package com.example.demo.service.user;

import com.example.demo.repository.model.dto.CommentDto;

public interface UserService {
    void setStatus(String email, String status, Long id) throws Exception;

    void addCommentTask(String email, Long id, CommentDto commentDto) throws Exception;
}
