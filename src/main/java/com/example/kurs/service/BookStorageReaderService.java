package com.example.kurs.service;

import com.example.kurs.domain.entity.BookStorageReader;
import com.example.kurs.dto.BookStorageReaderDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.BookStorageReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class BookStorageReaderService {
    private final BookStorageReaderRepository bookStorageReaderRepository;

    @Autowired
    public BookStorageReaderService(BookStorageReaderRepository bookStorageReaderRepository) {
        this.bookStorageReaderRepository = bookStorageReaderRepository;
    }

    public BookStorageReaderDto getById(Integer id) {
        return bookStorageReaderRepository.findById(id)
                .map(bookStorageReader -> CommonMapper.map(bookStorageReader, BookStorageReaderDto.class))
                .orElse(null);
    }

    public List<BookStorageReaderDto> getAll() {
        return bookStorageReaderRepository.findAll().stream()
                .map(bookStorageReader -> CommonMapper.map(bookStorageReader, BookStorageReaderDto.class))
                .collect(toList());
    }

    public List<BookStorageReaderDto> getAllDebtors() {
        return bookStorageReaderRepository.findAll().stream()
                .filter(BookStorageReader::isDebtor)
                .map(bookStorageReader -> CommonMapper.map(bookStorageReader, BookStorageReaderDto.class))
                .collect(toList());
    }

    @Transactional
    public void create(BookStorageReaderDto bookStorageReaderDtoToCreate) {
        var bookStorageDto = bookStorageReaderDtoToCreate.getBookStorage();
        var readerDto = bookStorageReaderDtoToCreate.getReader();
        var savedBookStorageReaderDto = getByBookStorageAndReaderIds(
                bookStorageDto.getId(),
                readerDto.getId()
        );
        if (ObjectUtils.isEmpty(savedBookStorageReaderDto)) {
            savedBookStorageReaderDto = bookStorageReaderDtoToCreate;

        } else {
            savedBookStorageReaderDto.setBookStorage(bookStorageDto);
            savedBookStorageReaderDto.setReader(readerDto);
        }
        bookStorageReaderRepository.save(CommonMapper.map(savedBookStorageReaderDto, BookStorageReader.class));
    }

    @Transactional
    public void edit(BookStorageReaderDto bookStorageReaderDtoToEdit) {
        bookStorageReaderRepository.save(CommonMapper.map(bookStorageReaderDtoToEdit, BookStorageReader.class));
    }

    @Transactional
    public void deleteById(Integer bookStorageReaderId) {
        bookStorageReaderRepository.deleteById(bookStorageReaderId);
    }

    public BookStorageReaderDto getByBookStorageAndReaderIds(Integer bookStorageId, Integer readerId) {
        return Optional.ofNullable(bookStorageReaderRepository.findByBookStorageIdAndReaderId(bookStorageId, readerId))
                .map(bookStorageReader -> CommonMapper.map(bookStorageReader, BookStorageReaderDto.class))
                .orElse(null);
    }
}
