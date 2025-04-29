package com.example.library.service;

import com.example.library.dto.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.time.LocalDate; // Добавляем импорт для LocalDate
import java.util.List;
import java.util.Set;

@Service
public class BookService {

    private final JdbcTemplate jdbcTemplate;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public BookService(JdbcTemplate jdbcTemplate, ModelMapper modelMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.modelMapper = modelMapper;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    /**
     * Creates a new book with validation.
     *
     * @param bookDTO the data transfer object containing book details
     * @return the created book as a data transfer object
     * @throws IllegalArgumentException if validation fails
     */
    public BookDTO createBook(BookDTO bookDTO) {
        // Validate input data
        Set<ConstraintViolation<BookDTO>> violations = validator.validate(bookDTO);
        if (!violations.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("Validation failed: ");
            for (ConstraintViolation<BookDTO> violation : violations) {
                errorMessage.append(violation.getMessage()).append("; ");
            }
            throw new IllegalArgumentException(errorMessage.toString());
        }

        // Insert the new book into the database
        String sql = "INSERT INTO book (title, author_id, publisher_id, category_id, publication_date) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                bookDTO.getTitle(),
                bookDTO.getAuthorId(),
                bookDTO.getPublisherId(),
                bookDTO.getCategoryId(),
                bookDTO.getPublicationDate()
        );

        // Get the last inserted ID
        String sqlGetLastId = "SELECT LAST_INSERT_ID()";
        Long lastInsertId = jdbcTemplate.queryForObject(sqlGetLastId, Long.class);

        // Set the ID in the DTO and return it
        bookDTO.setId(lastInsertId);
        return bookDTO;
    }

    /**
     * Retrieves all books.
     *
     * @return a list of books as data transfer objects
     */
    public List<BookDTO> getAllBooks() {
        String sql = "SELECT id, title, author_id, publisher_id, category_id, publication_date FROM book";

        RowMapper<BookDTO> rowMapper = (rs, rowNum) -> new BookDTO(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getLong("author_id"),
                rs.getLong("publisher_id"),
                rs.getLong("category_id"),
                rs.getObject("publication_date", LocalDate.class)  // Использование LocalDate
        );

        return jdbcTemplate.query(sql, rowMapper);
    }
}
