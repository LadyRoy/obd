package com.example.kurs.mapper.fxml;

import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.ReaderDto;
import com.example.kurs.dto.fxml.AuthorFxmlDto;
import com.example.kurs.dto.fxml.ReaderFxmlDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.springframework.util.ObjectUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public final class ReaderFxmlDtoMapper {

    private ReaderFxmlDtoMapper() {
    }

    public static ReaderFxmlDto mapToDto(ReaderDto readerDto) {
        return new ReaderFxmlDto(
                new SimpleIntegerProperty(readerDto.getId()),
                new SimpleStringProperty(readerDto.getFirstName()),
                new SimpleStringProperty(readerDto.getLastName()),
                new SimpleStringProperty(readerDto.getPhoneNumber()),
                new SimpleStringProperty(readerDto.getAddress()),
                new SimpleStringProperty(readerDto.getMail()),
                new SimpleStringProperty(mapBookNamesToString(readerDto.getBooks()))
        );
    }

    private static String mapBookNamesToString(List<BookDto> bookDtos) {
        if (ObjectUtils.isEmpty(bookDtos)) {
            return "";
        }
        return bookDtos.stream()
                .map(BookDto::getName)
                .collect(Collectors.joining(",\n"));
    }
}
