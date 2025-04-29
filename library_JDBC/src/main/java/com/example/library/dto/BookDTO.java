package com.example.library.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class BookDTO {

    private Long id;

    @NotNull(message = "Title cannot be null")
    @Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
    private String title;

    @NotNull(message = "Author cannot be null")
    private Long authorId;

    @NotNull(message = "Publisher cannot be null")
    private Long publisherId;

    @NotNull(message = "Category cannot be null")
    private Long categoryId;

    @NotNull(message = "Publication date cannot be null")
    private LocalDate publicationDate;

    public BookDTO(Long id, String title, Long authorId, Long publisherId, Long categoryId, LocalDate publicationDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
        this.publicationDate = publicationDate;
    }
}
