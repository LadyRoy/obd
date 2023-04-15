package com.example.kurs.dto.fxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class BookStorageReaderFxmlDto {
    private SimpleIntegerProperty id;
    private SimpleStringProperty book;
    private SimpleStringProperty reader;
    private SimpleStringProperty claimDate;
    private SimpleStringProperty returnDate;

    public BookStorageReaderFxmlDto(SimpleIntegerProperty id,
                                    SimpleStringProperty book,
                                    SimpleStringProperty reader,
                                    SimpleStringProperty claimDate,
                                    SimpleStringProperty returnDate) {

        this.id = id;
        this.book = book;
        this.reader = reader;
        this.claimDate = claimDate;
        this.returnDate = returnDate;
    }

    public BookStorageReaderFxmlDto() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getBook() {
        return book.get();
    }

    public SimpleStringProperty bookProperty() {
        return book;
    }

    public void setBook(String book) {
        this.book.set(book);
    }

    public String getReader() {
        return reader.get();
    }

    public SimpleStringProperty readerProperty() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader.set(reader);
    }

    public String getClaimDate() {
        return claimDate.get();
    }

    public SimpleStringProperty claimDateProperty() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate.set(claimDate);
    }

    public String getReturnDate() {
        return returnDate.get();
    }

    public SimpleStringProperty returnDateProperty() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate.set(returnDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStorageReaderFxmlDto that = (BookStorageReaderFxmlDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(book, that.book)
                && Objects.equals(reader, that.reader)
                && Objects.equals(claimDate, that.claimDate)
                && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, reader, claimDate, returnDate);
    }

    @Override
    public String toString() {
        return "BookStorageReaderFxmlDto{" +
                "id=" + id +
                ", book=" + book +
                ", reader=" + reader +
                ", claimDate=" + claimDate +
                ", returnDate=" + returnDate +
                '}';
    }
}
