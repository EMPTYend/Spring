package com.example.library.controller;

import com.example.library.dto.PublisherDTO;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing publishers.
 * Provides endpoints for creating and retrieving publishers.
 */
@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    // Внедрение зависимости PublisherService
    @Autowired
    private PublisherService publisherService;

    /**
     * Creates a new publisher.
     *
     * @param dto the PublisherDTO object containing publisher details
     * @return the created PublisherDTO object
     */
    @PostMapping
    public PublisherDTO create(@RequestBody PublisherDTO dto) {
        return publisherService.createPublisher(dto);
    }

    /**
     * Retrieves a list of all publishers.
     *
     * @return a list of PublisherDTO objects representing all publishers
     */
    @GetMapping
    public List<PublisherDTO> getAll() {
        return publisherService.getAllPublishers();
    }
}
