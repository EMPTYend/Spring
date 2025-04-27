package com.example.library.service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.example.library.dto.BookDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing books in the library.
 * Provides methods for creating and retrieving books.
 */
@Service
public class BookService {

    /**
     * Repository for performing CRUD operations on Book entities.
     */
    private final BookRepository bookRepository;

    /**
     * Utility for mapping between DTOs and entities.
     */
    private final ModelMapper modelMapper;

    /**
     * Constructs a new BookService with the specified dependencies.
     *
     * @param bookRepository the repository for managing Book entities
     * @param modelMapper    the mapper for converting between entities and DTOs
     */
    public BookService(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Creates a new book in the library.
     *
     * @param bookDTO the data transfer object containing book details
     * @return the created book as a data transfer object
     */
    public BookDTO createBook(BookDTO bookDTO) {
        // Преобразуем DTO в сущность
        Book book = modelMapper.map(bookDTO, Book.class);
        // Сохраняем книгу в базе данных
        book = bookRepository.save(book);
        // Возвращаем сохраненную книгу как DTO
        return modelMapper.map(book, BookDTO.class);
    }

    /**
     * Retrieves all books from the library.
     *
     * @return a list of books as data transfer objects
     */
    public List<BookDTO> getAllBooks() {
        // Извлекаем все книги из базы данных и преобразуем в DTO
        return bookRepository.findAll().stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
}
