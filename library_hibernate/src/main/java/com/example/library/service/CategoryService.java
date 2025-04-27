package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import com.example.library.entity.Category;
import com.example.library.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing categories.
 * Provides methods for creating and retrieving categories.
 */
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructor for CategoryService.
     *
     * @param categoryRepository the repository for managing category entities
     * @param modelMapper        the mapper for converting between entities and DTOs
     */
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new category.
     *
     * @param categoryDTO the data transfer object containing category details
     * @return the created category as a data transfer object
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of all categories as data transfer objects
     */
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(category -> modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }
}
