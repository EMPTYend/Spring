package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.example.library.dto.BookDTO;
import java.util.List;
import java.util.stream.Collectors;  // добавьте этот импорт

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }
    
    public BookDTO createBook(BookDTO bookDTO) {
        // Преобразование DTO в сущность
        Book book = modelMapper.map(bookDTO, Book.class); 
        Book savedBook = bookRepository.save(book); // здесь Book должен быть сущностью из пакета entity
        return modelMapper.map(savedBook, BookDTO.class);
    }
    
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        // Преобразование списка сущностей в список DTO
        return books.stream()
                    .map(book -> modelMapper.map(book, BookDTO.class))
                    .collect(Collectors.toList());
    }
}
