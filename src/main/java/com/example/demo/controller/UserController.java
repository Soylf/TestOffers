package com.example.demo.controller;

import com.example.demo.controller.validators.EnumValidation;
import com.example.demo.model.dto.CommentDto;
import com.example.demo.model.enums.Status;
import com.example.demo.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Пользователь", description = "управление задачами пользователя")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PatchMapping("/{taskId}")
    @Operation(summary = "Изменить статус задачи ы которой вы исполнитель")
    @ApiResponse(responseCode = "200", description = "Статус задачи изменен")
    public void setStatus(@PathVariable String email,
                          @PathVariable(name = "taskId") @Positive Long id,
                          @PathVariable(name = "status") @EnumValidation(enumClass = Status.class) String status) throws Exception {
        service.setStatus(email, status, id);
    }

    @PatchMapping("/comment/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Добавить комменатрий к задачи ы которой вы исполнитель")
    @ApiResponse(responseCode = "200", description = "комменатрий добавлен")
    public void addCommentTask(@PathVariable String email,
                               @PathVariable(name = "taskId") @Positive Long id,
                               @RequestBody CommentDto commentDto) throws Exception {
        service.addCommentTask(email, id, commentDto);
    }
}
