package com.example.kurs.mapper;

import com.example.kurs.domain.entity.Book;
import com.example.kurs.domain.entity.BookStorage;
import com.example.kurs.domain.entity.BookStorageReader;
import com.example.kurs.domain.entity.Reader;
import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.BookStorageDto;
import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.dto.ReaderDto;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public final class ReaderDtoMapper {

    private ReaderDtoMapper() {
    }

    public static ReaderDto mapToDto(Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getPhoneNumber(),
                reader.getAddress(),
                reader.getMail(),
                getBooks(reader.getBookStorageReaders())
        );
    }

    private static List<BookDto> getBooks(List<BookStorageReader> bookStorageReaders) {
        return bookStorageReaders.stream()
                .map(BookStorageReader::getBookStorage)
                .map(BookStorage::getBook)
                .map(BookDtoMapper::mapToDto)
                .collect(toList());
    }
}
