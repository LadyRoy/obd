package com.example.kurs.service;

import com.example.kurs.domain.entity.PublishingHouse;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.PublishingHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class PublishingHouseService {
    private final PublishingHouseRepository publishingHouseRepository;

    @Autowired
    public PublishingHouseService(PublishingHouseRepository publishingHouseRepository) {
        this.publishingHouseRepository = publishingHouseRepository;
    }

    public List<BookDto> getBooksByPublishingHouseId(Integer publishingHouseId) {
        return publishingHouseRepository.findById(publishingHouseId)
                .map(PublishingHouse::getReleasedBooks)
                .stream()
                .flatMap(Collection::stream)
                .map(book -> CommonMapper.map(book, BookDto.class))
                .collect(toList());
    }

    public List<PublishingHouseDto> getAll() {
        return publishingHouseRepository.findAll().stream()
                .map(publishingHouse -> CommonMapper.map(publishingHouse, PublishingHouseDto.class))
                .collect(toList());
    }

    @Transactional
    public PublishingHouseDto create(PublishingHouseDto publishingHouseDtoToCreate) {
        return Optional.ofNullable(publishingHouseDtoToCreate)
                .map(publishingHouseDto -> CommonMapper.map(publishingHouseDto, PublishingHouse.class))
                .map(publishingHouseRepository::save)
                .map(publishingHouse -> CommonMapper.map(publishingHouse, PublishingHouseDto.class))
                .orElse(null);
    }

    @Transactional
    public PublishingHouseDto edit(PublishingHouseDto publishingHouseDtoToEdit) {
        if (ObjectUtils.isEmpty(publishingHouseDtoToEdit.getId())) {
            return null;
        }
        return create(publishingHouseDtoToEdit);
    }

    @Transactional
    public void deleteById(Integer publishingHouseId) {
        Optional.ofNullable(publishingHouseId)
                .ifPresent(publishingHouseRepository::deleteById);
    }
}
