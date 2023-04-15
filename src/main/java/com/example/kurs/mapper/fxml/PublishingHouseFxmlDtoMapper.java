package com.example.kurs.mapper.fxml;

import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.dto.fxml.PublishingHouseFxmlDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.List;
import java.util.stream.Collectors;

public final class PublishingHouseFxmlDtoMapper {

    private PublishingHouseFxmlDtoMapper() {
    }

    public static PublishingHouseFxmlDto mapToDto(PublishingHouseDto publishingHouseDto, List<BookDto> bookDtos) {
        return new PublishingHouseFxmlDto(
                new SimpleIntegerProperty(publishingHouseDto.getId()),
                new SimpleStringProperty(publishingHouseDto.getName()),
                new SimpleStringProperty(publishingHouseDto.getLocation()),
                new SimpleIntegerProperty(bookDtos.size()),
                new SimpleStringProperty(mapBookNamesToString(bookDtos))
        );
    }

    private static String mapBookNamesToString(List<BookDto> bookDtos) {
        return bookDtos.stream()
                .map(BookDto::getName)
                .collect(Collectors.joining(",\n"));
    }
}
