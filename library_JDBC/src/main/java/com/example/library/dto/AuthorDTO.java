package com.example.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AuthorDTO {

    private Long id;

    @NotBlank(message = "Name cannot be empty")  // Проверка на пустое поле
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")  // Длина имени
    private String name;

    @NotBlank(message = "Biography cannot be empty")  // Проверка на пустое поле
    @Size(max = 500, message = "Biography must be less than 500 characters")  // Ограничение на длину биографии
    private String biography;

    // Конструктор, геттеры и сеттеры

    public AuthorDTO(Long id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
