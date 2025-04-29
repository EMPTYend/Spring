package com.example.library.dto;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
public class PublisherDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Country cannot be null")
    private String country;

    public PublisherDTO(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }
}
