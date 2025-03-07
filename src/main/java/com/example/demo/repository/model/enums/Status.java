package com.example.demo.repository.model.enums;

import lombok.Getter;

@Getter
public enum Status {
    PENDING("В ожидании"),
    IN_PROGRESS("В процессе"),
    COMPLETED("Завершено");

    private final String description;

    Status(String description) {
        this.description = description;
    }

}
