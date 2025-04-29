package com.example.library.service;

import com.example.library.dto.CategoryDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public CategoryService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new category with validation.
     *
     * @param categoryDTO the data transfer object containing category details
     * @return the created category as a data transfer object
     * @throws IllegalArgumentException if validation fails
     */
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        // Validate input data
        Set<jakarta.validation.ConstraintViolation<CategoryDTO>> violations = validator.validate(categoryDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (jakarta.validation.ConstraintViolation<CategoryDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Insert the new category into the database
        String sql = "INSERT INTO category (name) VALUES (?)";

        jdbcTemplate.update(sql, categoryDTO.getName());

        // Get the last inserted ID
        String sqlGetLastId = "SELECT LAST_INSERT_ID()";
        Long lastInsertId = jdbcTemplate.queryForObject(sqlGetLastId, Long.class);

        // Set the ID in the DTO and return it
        categoryDTO.setId(lastInsertId);
        return categoryDTO;
    }

    /**
     * Retrieves all categories.
     *
     * @return a list of categories as data transfer objects
     */
    public List<CategoryDTO> getAllCategories() {
        String sql = "SELECT id, name FROM category";

        RowMapper<CategoryDTO> rowMapper = (rs, rowNum) -> new CategoryDTO(
                rs.getLong("id"),
                rs.getString("name")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
