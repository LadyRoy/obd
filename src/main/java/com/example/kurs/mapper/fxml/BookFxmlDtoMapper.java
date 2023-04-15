package com.example.kurs.mapper.fxml;

import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.dto.fxml.BookFxmlDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;

public final class BookFxmlDtoMapper {

    private BookFxmlDtoMapper() {
    }

    public static BookFxmlDto mapToDto(BookDto bookDto) {
        return new BookFxmlDto(
                new SimpleIntegerProperty(bookDto.getId()),
                new SimpleStringProperty(bookDto.getName()),
                new SimpleStringProperty(getAuthor(bookDto.getAuthor())),
                new SimpleStringProperty(getPublishingHouse(bookDto.getPublishingHouse())),
                new SimpleStringProperty(bookDto.getBookStorage().getStorage().getLibraryLocation()),
                new SimpleStringProperty(bookDto.getBookStorage().getSection()),
                new SimpleStringProperty(bookDto.getReleaseDate().format(DateTimeFormatter.ISO_LOCAL_DATE)),
                new SimpleStringProperty(bookDto.getRegisterDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
        );
    }

    private static String getAuthor(AuthorDto authorDto) {
        return authorDto.getSurname() + " " + authorDto.getName();
    }

    private static String getPublishingHouse(PublishingHouseDto publishingHouseDto) {
        return publishingHouseDto.getName() + ", " + publishingHouseDto.getLocation();
    }
}
