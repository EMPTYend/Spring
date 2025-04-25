package com.example.library.controller;

import com.example.library.dto.*;
import com.example.library.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.modelmapper.ModelMapper;
import com.example.library.dto.BookDTO;



@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final BookService bookService;
    private final ModelMapper modelMapper;
    
    public BookController(BookService bookService, ModelMapper modelMapper) {
        this.bookService = bookService;
        this.modelMapper = modelMapper;
    }
    
    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookService.createBook(bookDTO);
    }
    
    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }
}

