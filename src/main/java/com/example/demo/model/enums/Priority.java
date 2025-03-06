package com.example.demo.model.enums;

import lombok.Getter;

@Getter
public enum Priority {
    HIGH("Высокий"),
    MEDIUM("Средний"),
    LOW("Низкий");

    private final String description;

    Priority(String description) {
        this.description = description;
    }
}
