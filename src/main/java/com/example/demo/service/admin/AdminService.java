package com.example.demo.service.admin;

import com.example.demo.model.Comment;
import com.example.demo.model.Task;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminService {
    void createTask(Task task);

    void updateTask(Long id, Task task);

    Task getTask(Long id);

    void deleteTask(Long id);

    void setPriorityTask(Long id, String priority);

    void setStatusTask(Long id, String status);

    void updatePerformer(Long taskId, Long userId);

    void giveCommentTask(Long id, Comment comment);

    List<Task> getTasks(Pageable pageable);
}
