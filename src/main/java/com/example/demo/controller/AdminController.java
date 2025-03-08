package com.example.demo.controller;

import com.example.demo.controller.validators.EnumValidation;
import com.example.demo.model.entity.Task;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.dto.TaskDto;
import com.example.demo.model.enums.Priority;
import com.example.demo.model.enums.Status;
import com.example.demo.service.admin.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Администратор", description = "Управление задачами администратора")
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService service;

    @PostMapping
    @Operation(summary = "Создать задачу", description = "Создает новую задачу и назначает её пользователю")
    @ApiResponse(responseCode = "200", description = "Задача создана")
    public Task createTask(@RequestParam String email,
                           @RequestBody TaskDto taskDto) {
        return service.createTask(taskDto,email);
    }

    @PatchMapping("/{taskId}")
    @Operation(summary = "Обновить задачу", description = "Обновляет задачу")
    @ApiResponse(responseCode = "200", description = "Задача обновить")
    public void updateTask(@PathVariable(name = "taskId") @Positive Long id,
                           @RequestBody TaskDto taskDto) {
        service.updateTask(id, taskDto);
    }

    @PatchMapping("/{taskId}/priority/{priority}")
    @Operation(summary = "Изменить приоритет задачи", description = "Изменяет приоритет задачи")
    @ApiResponse(responseCode = "200", description = "Приоритет задачи изменен")
    public void setPriorityTask(@PathVariable(name = "taskId") @Positive Long id,
                                @PathVariable(name = "priority") @EnumValidation(enumClass = Priority.class) String priority) {
        service.setPriorityTask(id, priority);
    }

    @PatchMapping("/{taskId}/status/{status}")
    @Operation(summary = "Изменить статус задачи", description = "Изменяет статус задачи")
    @ApiResponse(responseCode = "200", description = "Статус задачи изменен")
    public void setStatusTask(@PathVariable(name = "taskId") @Positive Long id,
                              @PathVariable(name = "status") @EnumValidation(enumClass = Status.class) String status) {
        service.setStatusTask(id, status);
    }

    @PatchMapping("/{taskId}/{userId}")
    @Operation(summary = "Исполнитель изменен", description = "Изменяет исполнителя задачи")
    @ApiResponse(responseCode = "200", description = "исполнитель задачи изменен")
    public void updatePerformer(@PathVariable(name = "taskId") @Positive Long taskId,
                                @PathVariable(name = "userId") @Positive Long userId) {
        service.updatePerformer(taskId, userId);
    }

    @PatchMapping("/{taskId}/comment")
    @Operation(summary = "Добовляет комментарий от администратора", description = "Добовляет комментарий от администратора")
    @ApiResponse(responseCode = "200", description = "Комменатрий админа добавлен")
    public void giveCommentTask(@PathVariable(name = "taskId") @Positive Long id,
                                @RequestBody CommentDto commentDto) {
        service.giveCommentTask(id, commentDto);
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Удаление задачи", description = "Удаляет задачу")
    @ApiResponse(responseCode = "200", description = "Задача удалена")
    public void deleteTask(@PathVariable(name = "taskId") Long id) {
        service.deleteTask(id);
    }

    @GetMapping
    @Operation(summary = "Показать задачи", description = "Показывает задачи")
    @ApiResponse(responseCode = "200", description = "Задачи показаны")
    public List<Task> getTasks(@PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.getTasks(pageable);
    }
}