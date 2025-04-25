package com.example.library.controller;

import com.example.library.dto.PublisherDTO;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    // Метод для создания издателя
    @PostMapping
    public PublisherDTO create(@RequestBody PublisherDTO dto) {
        return publisherService.createPublisher(dto); // Создание издателя
    }

    // Метод для получения всех издателей
    @GetMapping
    public List<PublisherDTO> getAll() {
        return publisherService.getAllPublishers(); // Получение списка издателей в виде DTO
    }
}
