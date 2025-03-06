package com.example.demo.service.admin;

import com.example.demo.model.Comment;
import com.example.demo.model.Task;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminServiceImpl implements AdminService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public void createTask(Task task) {

    }

    @Override
    public void updateTask(Long id, Task task) {

    }

    @Override
    public Task getTask(Long id) {
        return null;
    }

    @Override
    public void deleteTask(Long id) {

    }

    @Override
    public void setPriorityTask(Long id, String priority) {

    }

    @Override
    public void setStatusTask(Long id, String status) {

    }

    @Override
    public void updatePerformer(Long taskId, Long userId) {

    }

    @Override
    public void giveCommentTask(Long id, Comment comment) {

    }

    @Override
    public List<Task> getTasks(Pageable pageable) {
        return List.of();
    }
}
