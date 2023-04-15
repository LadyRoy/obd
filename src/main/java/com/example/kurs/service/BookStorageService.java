package com.example.kurs.service;

import com.example.kurs.domain.entity.BookStorage;
import com.example.kurs.dto.BookStorageDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.BookStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookStorageService {
    private final BookStorageRepository bookStorageRepository;

    @Autowired
    public BookStorageService(BookStorageRepository bookStorageRepository) {
        this.bookStorageRepository = bookStorageRepository;
    }

    @Transactional
    public void create(BookStorageDto bookStorageDtoToCreate) {
        var bookDto = bookStorageDtoToCreate.getBook();
        var storageDto = bookStorageDtoToCreate.getStorage();
        var savedBookStorageDto = getByBookAndStorageIds(
                bookDto.getId(),
                storageDto.getId()
        );
        if (ObjectUtils.isEmpty(savedBookStorageDto)) {
            savedBookStorageDto = bookStorageDtoToCreate;

        } else {
            savedBookStorageDto.setBook(bookDto);
            savedBookStorageDto.setStorage(storageDto);
            savedBookStorageDto.setSection(bookStorageDtoToCreate.getSection());
        }
        bookStorageRepository.save(CommonMapper.map(savedBookStorageDto, BookStorage.class));
    }

    public BookStorageDto getByBookAndStorageIds(Integer bookId, Integer storageId) {
        return Optional.ofNullable(bookStorageRepository.findByBookIdAndStorageId(bookId, storageId))
                .map(bookStorage -> CommonMapper.map(bookStorage, BookStorageDto.class))
                .orElse(null);
    }
}
