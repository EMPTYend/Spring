package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import com.example.library.entity.Category;
import com.example.library.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    // Метод для создания категории
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class); // Преобразуем DTO в сущность
        Category savedCategory = categoryRepository.save(category); // Сохраняем категорию
        return modelMapper.map(savedCategory, CategoryDTO.class); // Возвращаем сохраненную категорию как DTO
    }

    // Метод для получения всех категорий
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                         .map(category -> modelMapper.map(category, CategoryDTO.class)) // Преобразуем сущности в DTO
                         .collect(Collectors.toList());
    }
}
