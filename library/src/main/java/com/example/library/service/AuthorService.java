package com.example.library.service;

import com.example.library.dto.AuthorDTO;
import com.example.library.entity.Author;
import com.example.library.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public AuthorService(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    // Метод для создания автора
    public AuthorDTO createAuthor(AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class); // Преобразуем DTO в сущность
        Author savedAuthor = authorRepository.save(author); // Сохраняем автора
        return modelMapper.map(savedAuthor, AuthorDTO.class); // Возвращаем сохраненного автора как DTO
    }

    // Метод для получения всех авторов
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                      .map(author -> modelMapper.map(author, AuthorDTO.class)) // Преобразуем сущности в DTO
                      .collect(Collectors.toList());
    }
}
