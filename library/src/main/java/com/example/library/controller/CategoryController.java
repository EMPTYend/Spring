package com.example.library.controller;

import com.example.library.dto.CategoryDTO;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Метод для создания категории
    @PostMapping
    public CategoryDTO create(@RequestBody CategoryDTO dto) {
        return categoryService.createCategory(dto); // Создание категории
    }

    // Метод для получения всех категорий
    @GetMapping
    public List<CategoryDTO> getAll() {
        return categoryService.getAllCategories(); // Получение списка категорий в виде DTO
    }
}
