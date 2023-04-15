package com.example.kurs.service;

import com.example.kurs.domain.entity.Reader;
import com.example.kurs.dto.BookStorageReaderDto;
import com.example.kurs.dto.ReaderDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.mapper.ReaderDtoMapper;
import com.example.kurs.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class ReaderService {
    private final BookStorageReaderService bookStorageReaderService;
    private final ReaderRepository readerRepository;

    @Autowired
    public ReaderService(BookStorageReaderService bookStorageReaderService,
                         ReaderRepository readerRepository) {

        this.bookStorageReaderService = bookStorageReaderService;
        this.readerRepository = readerRepository;
    }

    public List<ReaderDto> getAll() {
        return readerRepository.findAll().stream()
                .map(ReaderDtoMapper::mapToDto)
                .collect(toList());
    }

    @Transactional
    public ReaderDto create(ReaderDto readerDtoToCreate) {
        return Optional.ofNullable(readerDtoToCreate)
                .map(readerDto -> CommonMapper.map(readerDto, Reader.class))
                .map(readerRepository::save)
                .map(reader -> CommonMapper.map(reader, ReaderDto.class))
                .orElse(null);
    }

    @Transactional
    public ReaderDto edit(ReaderDto readerDtoToEdit) {
        if (ObjectUtils.isEmpty(readerDtoToEdit.getId())) {
            return null;
        }
        return create(readerDtoToEdit);
    }

    public List<BookStorageReaderDto> getAllDebtors() {
        return bookStorageReaderService.getAllDebtors();
    }

    @Transactional
    public void processDebtor(BookStorageReaderDto bookStorageReaderDtoToEdit) {
        bookStorageReaderDtoToEdit.setDebtor(!bookStorageReaderDtoToEdit.isDebtor());
        bookStorageReaderService.edit(bookStorageReaderDtoToEdit);
    }

    @Transactional
    public void deleteById(Integer readerId) {
        Optional.ofNullable(readerId)
                .ifPresent(readerRepository::deleteById);
    }
}
