package com.example.library.controller;

import com.example.library.dto.LibraryDTO;
import com.example.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing library-related operations.
 * Provides endpoints for creating and retrieving libraries.
 * 
 * Endpoints:
 * - POST /api/libraries: Create a new library.
 * - GET /api/libraries: Retrieve a list of all libraries.
 * 
 * Dependencies:
 * - LibraryService: Service layer for handling library business logic.
 */
@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // Метод для создания библиотеки
    @PostMapping
    public LibraryDTO create(@RequestBody LibraryDTO dto) {
        return libraryService.createLibrary(dto); // Создание библиотеки
    }

    // Метод для получения всех библиотек
    @GetMapping
    public List<LibraryDTO> getAll() {
        return libraryService.getAllLibraries(); // Получение списка библиотек в виде DTO
    }
}
