package com.example.library.service;

import com.example.library.dto.LibraryDTO;
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
public class LibraryService {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public LibraryService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new library with validation.
     *
     * @param libraryDTO the data transfer object containing library details
     * @return the created library as a data transfer object
     * @throws IllegalArgumentException if validation fails
     */
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        // Validate input data
        Set<jakarta.validation.ConstraintViolation<LibraryDTO>> violations = validator.validate(libraryDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (jakarta.validation.ConstraintViolation<LibraryDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Insert the new library into the database
        String sql = "INSERT INTO library (name, address) VALUES (?, ?)";

        jdbcTemplate.update(sql, libraryDTO.getName(), libraryDTO.getAddress());

        // Get the last inserted ID
        String sqlGetLastId = "SELECT LAST_INSERT_ID()";
        Long lastInsertId = jdbcTemplate.queryForObject(sqlGetLastId, Long.class);

        // Set the ID in the DTO and return it
        libraryDTO.setId(lastInsertId);
        return libraryDTO;
    }

    /**
     * Retrieves all libraries.
     *
     * @return a list of libraries as data transfer objects
     */
    public List<LibraryDTO> getAllLibraries() {
        String sql = "SELECT id, name, address FROM library";

        RowMapper<LibraryDTO> rowMapper = (rs, rowNum) -> new LibraryDTO(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("address")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
