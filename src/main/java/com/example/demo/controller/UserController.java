package com.example.demo.controller;

import com.example.demo.controller.validators.EnumValidation;
import com.example.demo.repository.model.dto.CommentDto;
import com.example.demo.repository.model.enums.Status;
import com.example.demo.service.user.UserService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService service;

    @PatchMapping("/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void setStatus(@PathVariable String email,
                          @PathVariable(name = "taskId") @Positive Long id,
                          @PathVariable(name = "status") @EnumValidation(enumClass = Status.class) String status) throws Exception {
        log.info("user: {} set status task", email);
        service.setStatus(email, status, id);
    }

    @PatchMapping("/comment/{taskId}")
    @ResponseStatus(HttpStatus.OK)
    public void addCommentTask(@PathVariable String email,
                               @PathVariable(name = "taskId") @Positive Long id,
                               @RequestBody CommentDto commentDto) throws Exception {
        log.info("user: {} add comment task {}", email, id);
        service.addCommentTask(email, id, commentDto);
    }
}
