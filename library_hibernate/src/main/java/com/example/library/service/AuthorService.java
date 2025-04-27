package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing authors.
 * Provides methods for creating and retrieving authors.
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs an instance of AuthorService with the specified dependencies.
     *
     * @param authorRepository the repository for managing author entities
     * @param modelMapper      the mapper for converting between entities and DTOs
     */
    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new author.
     *
     * @param authorDTO the data transfer object containing author details
     * @return the created author as a data transfer object
     */
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        Author savedAuthor = authorRepository.save(author);
        return modelMapper.map(savedAuthor, AuthorDTO.class);
    }

    /**
     * Retrieves all authors.
     *
     * @return a list of all authors as data transfer objects
     */
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> modelMapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }
}
