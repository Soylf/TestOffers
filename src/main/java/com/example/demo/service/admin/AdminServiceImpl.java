package com.example.demo.service.admin;

import com.example.demo.error.exception.SomethingWentWrongException;
import com.example.demo.repository.model.Comment;
import com.example.demo.repository.model.EntityUser;
import com.example.demo.repository.model.Task;
import com.example.demo.repository.model.dto.CommentDto;
import com.example.demo.repository.model.dto.TaskDto;
import com.example.demo.repository.model.mapper.TaskMapper;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminServiceImpl implements AdminService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(TaskDto taskDto,String email) {
        Task task = TaskMapper.MAPPER.fromDto(taskDto);
        task.setAuthor(userRepository.findByEmail(email)
                .orElseThrow(() -> new SomethingWentWrongException("Пользователь не найден")));
        return taskRepository.save(task);
    }

    @Override
    public void updateTask(Long id, TaskDto taskDto) {
        Task foundTask = getTask(id);

        if (taskDto.getDescription() != null) {
            foundTask.setDescription(taskDto.getDescription());
        }
        if (taskDto.getAuthorId() != null) {
            EntityUser newAuthor = userRepository.findById(taskDto.getAuthorId())
                    .orElseThrow(() -> new SomethingWentWrongException("Такого пользователя нет"));
            foundTask.setAuthor(newAuthor);
        }

        taskRepository.save(foundTask);
    }

    @Override
    public void updatePerformer(Long taskId, Long userId) {
        Task task = getTask(taskId);
        EntityUser user = userRepository.findById(userId)
                .orElseThrow(()-> new SomethingWentWrongException(String.format("Пользователь %s не найден", userId)));

        task.setPerformer(user);
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void setPriorityTask(Long id, String priority) {
        Task task = getTask(id);
        task.setPriority(priority);
        taskRepository.save(task);
    }

    @Override
    public void setStatusTask(Long id, String status) {
        Task task = getTask(id);
        task.setStatus(status);
        taskRepository.save(task);
    }

    @Override
    public void giveCommentTask(Long id, CommentDto commentDto) {
        Task task = getTask(id);
        Comment comment = new Comment();
        comment.setDescription(commentDto.getDescription());
        comment.setTask(task);
        commentRepository.save(comment);
    }

    @Override
    public List<Task> getTasks(Pageable pageable) {
        Page<Task> tasksPage = taskRepository.findAll(pageable);
        return tasksPage.getContent();
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new SomethingWentWrongException(String.format("Задача %s не найдена", id)));
    }
}
