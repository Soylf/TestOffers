package com.example.demo.service.admin;

import com.example.demo.model.entity.Task;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.dto.TaskDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    Task createTask(TaskDto taskDto, String email);

    void updateTask(Long id, TaskDto taskDto);

    void deleteTask(Long id);

    void setPriorityTask(Long id, String priority);

    void setStatusTask(Long id, String status);

    void updatePerformer(Long taskId, Long userId);

    void giveCommentTask(Long id, CommentDto commentDto);

    List<Task> getTasks(Pageable pageable);
}
