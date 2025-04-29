package com.example.library.service;

import com.example.library.dto.AuthorDTO;
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
public class AuthorService {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public AuthorService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new author with validation.
     *
     * @param authorDTO the data transfer object containing author details
     * @return the created author as a data transfer object
     * @throws IllegalArgumentException if validation fails
     */
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        // Validate input data
        Set<jakarta.validation.ConstraintViolation<AuthorDTO>> violations = validator.validate(authorDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (jakarta.validation.ConstraintViolation<AuthorDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Insert the new author into the database
        String sql = "INSERT INTO author (name, biography) VALUES (?, ?)"; // <-- Заменил country на biography
        jdbcTemplate.update(sql, authorDTO.getName(), authorDTO.getBiography());

        // Get the last inserted ID
        String sqlGetLastId = "SELECT LAST_INSERT_ID()";
        Long lastInsertId = jdbcTemplate.queryForObject(sqlGetLastId, Long.class);

        // Set the ID in the DTO and return it
        authorDTO.setId(lastInsertId);
        return authorDTO;
    }

    /**
     * Retrieves all authors.
     *
     * @return a list of authors as data transfer objects
     */
    public List<AuthorDTO> getAllAuthors() {
        String sql = "SELECT id, name, biography FROM author"; // <-- Заменил country на biography

        RowMapper<AuthorDTO> rowMapper = (rs, rowNum) -> new AuthorDTO(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("biography") // <-- Заменил country на biography
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
