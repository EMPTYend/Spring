package com.example.library.service;

import com.example.library.dto.PublisherDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
public class PublisherService {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public PublisherService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new publisher with validation.
     *
     * @param publisherDTO the data transfer object containing publisher details
     * @return the created publisher as a data transfer object
     * @throws IllegalArgumentException if validation fails
     */
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        // Validate input data
        Set<ConstraintViolation<PublisherDTO>> violations = validator.validate(publisherDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<PublisherDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Insert the new publisher into the database
        String sql = "INSERT INTO publisher (name, country) VALUES (?, ?)";
        jdbcTemplate.update(sql, publisherDTO.getName(), publisherDTO.getCountry());

        // Get the last inserted ID
        String sqlGetLastId = "SELECT LAST_INSERT_ID()";
        Long lastInsertId = jdbcTemplate.queryForObject(sqlGetLastId, Long.class);

        // Set the ID in the DTO and return it
        publisherDTO.setId(lastInsertId);
        return publisherDTO;
    }

    /**
     * Retrieves all publishers.
     *
     * @return a list of publishers as data transfer objects
     */
    public List<PublisherDTO> getAllPublishers() {
        String sql = "SELECT id, name, country FROM publisher";

        RowMapper<PublisherDTO> rowMapper = (rs, rowNum) -> new PublisherDTO(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("country")
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
