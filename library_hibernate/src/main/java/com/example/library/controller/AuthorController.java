package com.example.library.controller;

import com.example.library.dto.AuthorDTO;
import com.example.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing authors.
 * Provides endpoints for creating and retrieving authors.
 * 
 * Base URL: /api/authors
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    // Метод для создания автора
    @PostMapping
    public AuthorDTO create(@RequestBody AuthorDTO dto) {
        return authorService.createAuthor(dto); // Создание автора
    }

    // Метод для получения всех авторов
    @GetMapping
    public List<AuthorDTO> getAll() {
        return authorService.getAllAuthors(); // Получение списка авторов в виде DTO
    }
}
