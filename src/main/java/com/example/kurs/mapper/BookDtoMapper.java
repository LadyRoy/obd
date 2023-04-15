package com.example.kurs.mapper;

import com.example.kurs.domain.entity.Book;
import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.BookStorageDto;
import com.example.kurs.dto.PublishingHouseDto;

public final class BookDtoMapper {

    private BookDtoMapper() {
    }

    public static BookDto mapToDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getName(),
                CommonMapper.map(book.getAuthorBooks().get(0).getAuthor(), AuthorDto.class),
                CommonMapper.map(book.getPublishingHouse(), PublishingHouseDto.class),
                CommonMapper.map(book.getBookStorages().get(0), BookStorageDto.class),
                book.getReleaseDate(),
                book.getRegisterDate()
        );
    }
}
