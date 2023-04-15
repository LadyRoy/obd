package com.example.kurs.mapper.fxml;

import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.fxml.AuthorFxmlDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public final class AuthorFxmlDtoMapper {

    private AuthorFxmlDtoMapper() {
    }

    public static AuthorFxmlDto mapToDto(AuthorDto authorDto, List<BookDto> bookDtos) {
        return new AuthorFxmlDto(
                new SimpleIntegerProperty(authorDto.getId()),
                new SimpleStringProperty(authorDto.getSurname()),
                new SimpleStringProperty(authorDto.getName()),
                new SimpleStringProperty(authorDto.getBirthday().format(DateTimeFormatter.ISO_LOCAL_DATE)),
                new SimpleStringProperty(authorDto.getShortInfo()),
                new SimpleStringProperty(mapBookNamesToString(bookDtos))
        );
    }

    private static String mapBookNamesToString(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(BookDto::getName)
                .collect(Collectors.joining(",\n"));
    }
}
