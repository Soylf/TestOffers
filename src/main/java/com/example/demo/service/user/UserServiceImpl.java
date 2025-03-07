package com.example.demo.service.user;

import com.example.demo.error.exception.SomethingWentWrongException;
import com.example.demo.repository.model.Comment;
import com.example.demo.repository.model.EntityUser;
import com.example.demo.repository.model.Task;
import com.example.demo.repository.model.dto.CommentDto;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService{
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public void setStatus(String email, String status, Long id) {
        Task task = getTask(id);

        if (!task.getPerformer().getEmail().equals(email)) {
            throw new SomethingWentWrongException("Что-то пошло не так");
        }

        task.setStatus(status);
        taskRepository.save(task);
    }
    @Override
    public void addCommentTask(String email, Long id, CommentDto commentDto) {
        Task task = getTask(id);

        if (!task.getPerformer().getEmail().equals(email)) {
            throw new SomethingWentWrongException("Что-то пошло не так");
        }

        EntityUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new SomethingWentWrongException("Пользователь не найден"));

        Comment comment = new Comment();
        comment.setDescription(commentDto.getDescription());
        comment.setUser(user);
        comment.setTask(task);

        commentRepository.save(comment);

    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(()-> new SomethingWentWrongException(String.format("Задача %s не найдена", id)));
    }
}
