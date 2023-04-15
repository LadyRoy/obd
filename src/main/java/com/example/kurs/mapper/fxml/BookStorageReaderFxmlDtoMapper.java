package com.example.kurs.mapper.fxml;

import com.example.kurs.dto.BookStorageReaderDto;
import com.example.kurs.dto.fxml.BookStorageReaderFxmlDto;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.format.DateTimeFormatter;

public final class BookStorageReaderFxmlDtoMapper {

    private BookStorageReaderFxmlDtoMapper() {
    }

    public static BookStorageReaderFxmlDto mapToDto(BookStorageReaderDto bookStorageReaderDto) {
        return new BookStorageReaderFxmlDto(
                new SimpleIntegerProperty(bookStorageReaderDto.getId()),
                new SimpleStringProperty(bookStorageReaderDto.getBookStorage().getBook().getName()),
                new SimpleStringProperty(bookStorageReaderDto.getReader().getLastName() + " "
                        + bookStorageReaderDto.getReader().getFirstName()),
                new SimpleStringProperty(bookStorageReaderDto.getClaimDate().format(DateTimeFormatter.ISO_LOCAL_DATE)),
                new SimpleStringProperty(bookStorageReaderDto.getReturnDate().format(DateTimeFormatter.ISO_LOCAL_DATE))
        );
    }
}
