package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")  // Проверка на пустое поле
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")  // Длина названия категории
    private String name;

    // Конструктор по умолчанию
    public CategoryDTO() {
    }

    // Конструктор с параметрами
    public CategoryDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
