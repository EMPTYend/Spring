package com.example.library.service;

import com.example.library.dto.PublisherDTO;
import com.example.library.entity.Publisher;
import com.example.library.repository.PublisherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;
    private final ModelMapper modelMapper;

    public PublisherService(PublisherRepository publisherRepository, ModelMapper modelMapper) {
        this.publisherRepository = publisherRepository;
        this.modelMapper = modelMapper;
    }

    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class);
        publisher = publisherRepository.save(publisher);
        return modelMapper.map(publisher, PublisherDTO.class);
    }

    public List<PublisherDTO> getAllPublishers() {
        return publisherRepository.findAll().stream()
                .map(publisher -> modelMapper.map(publisher, PublisherDTO.class))
                .collect(Collectors.toList());
    }
}
