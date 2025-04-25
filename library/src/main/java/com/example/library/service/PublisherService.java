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

    // Метод для создания издателя
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = modelMapper.map(publisherDTO, Publisher.class); // Преобразуем DTO в сущность
        Publisher savedPublisher = publisherRepository.save(publisher); // Сохраняем издателя
        return modelMapper.map(savedPublisher, PublisherDTO.class); // Возвращаем сохраненного издателя как DTO
    }

    // Метод для получения всех издателей
    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream()
                         .map(publisher -> modelMapper.map(publisher, PublisherDTO.class)) // Преобразуем сущности в DTO
                         .collect(Collectors.toList());
    }
}
