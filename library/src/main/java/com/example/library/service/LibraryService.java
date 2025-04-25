package com.example.library.service;

import com.example.library.dto.LibraryDTO;
import com.example.library.entity.Library;
import com.example.library.repository.LibraryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    private final ModelMapper modelMapper;

    public LibraryService(LibraryRepository libraryRepository, ModelMapper modelMapper) {
        this.libraryRepository = libraryRepository;
        this.modelMapper = modelMapper;
    }

    // Метод для создания библиотеки
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = modelMapper.map(libraryDTO, Library.class); // Преобразуем DTO в сущность
        Library savedLibrary = libraryRepository.save(library); // Сохраняем библиотеку
        return modelMapper.map(savedLibrary, LibraryDTO.class); // Возвращаем сохраненную библиотеку как DTO
    }

    // Метод для получения всех библиотек
    public List<LibraryDTO> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        return libraries.stream()
                        .map(library -> modelMapper.map(library, LibraryDTO.class)) // Преобразуем сущности в DTO
                        .collect(Collectors.toList());
    }
}
