package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.entity.Library;
import com.example.library.repository.LibraryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing libraries.
 * Provides methods for creating and retrieving library data.
 */
@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;

    /**
     * Constructs a new LibraryService with the specified repository and model mapper.
     *
     * @param libraryRepository the repository for managing library entities
     * @param modelMapper       the model mapper for converting between entities and DTOs
     */
    public LibraryService(LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new library.
     *
     * @param libraryDTO the data transfer object containing library details
     * @return the created library as a data transfer object
     */
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = modelMapper.map(libraryDTO, Library.class);
        Library savedLibrary = libraryRepository.save(library);
        return modelMapper.map(savedLibrary, LibraryDTO.class);
    }

    /**
     * Retrieves all libraries.
     *
     * @return a list of all libraries as data transfer objects
     */
    public List<LibraryDTO> getAllLibraries() {
        return libraryRepository.findAll().stream()
                .map(library -> modelMapper.map(library, LibraryDTO.class))
                .collect(Collectors.toList());
    }
}
