package com.example.demo.controller;

import com.example.demo.controller.validators.EnumValidation;
import com.example.demo.repository.model.Task;
import com.example.demo.repository.model.dto.CommentDto;
import com.example.demo.repository.model.dto.TaskDto;
import com.example.demo.repository.model.enums.Priority;
import com.example.demo.repository.model.enums.Status;
import com.example.demo.service.admin.AdminService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Task createTask(@RequestParam String email,
                           @RequestBody TaskDto taskDto) {
        log.info("Admin: create_task");
        return service.createTask(taskDto,email);
    }

    @PatchMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTask(@PathVariable(name = "taskId") @Positive Long id,
                           @RequestBody TaskDto taskDto) {
        log.info("Admin: update_task");
        service.updateTask(id, taskDto);
    }

    @PatchMapping("/{taskId}/priority/{priority}")
    @ResponseStatus(HttpStatus.OK)
    public void setPriorityTask(@PathVariable(name = "taskId") @Positive Long id,
                                @PathVariable(name = "priority") @EnumValidation(enumClass = Priority.class) String priority) {
        log.info("admin: set_priority_task");
        service.setPriorityTask(id, priority);
    }

    @PatchMapping("/{taskId}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void setStatusTask(@PathVariable(name = "taskId") @Positive Long id,
                              @PathVariable(name = "status") @EnumValidation(enumClass = Status.class) String status) {
        log.info("Admin: set_status_task");
        service.setStatusTask(id, status);
    }

    @PatchMapping("/{taskId}/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePerformer(@PathVariable(name = "taskId") @Positive Long taskId,
                                @PathVariable(name = "userId") @Positive Long userId) {
        log.info("Admin: update_performer");
        service.updatePerformer(taskId, userId);
    }

    @PatchMapping("/{taskId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void giveCommentTask(@PathVariable(name = "taskId") @Positive Long id,
                                @RequestBody CommentDto commentDto) {
        log.info("Admin: give_comment_task");
        service.giveCommentTask(id, commentDto);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable(name = "taskId") Long id) {
        log.info("Admin: delete_task");
        service.deleteTask(id);
    }

    @GetMapping
    public List<Task> getTasks(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        log.info("Admin: get_tasks");
        return service.getTasks(pageable);
    }
}