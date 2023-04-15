package com.example.kurs.dto.report;

import com.example.kurs.dto.fxml.BookStorageReaderFxmlDto;

import java.util.Objects;

public class BookStorageReaderReportDto {
    private String book;
    private String reader;
    private String claimDate;
    private String returnDate;

    public BookStorageReaderReportDto(BookStorageReaderFxmlDto bookStorageReaderFxmlDto) {
        this.book = bookStorageReaderFxmlDto.getBook();
        this.reader = bookStorageReaderFxmlDto.getReader();
        this.claimDate = bookStorageReaderFxmlDto.getClaimDate();
        this.returnDate = bookStorageReaderFxmlDto.getReturnDate();
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStorageReaderReportDto that = (BookStorageReaderReportDto) o;
        return Objects.equals(book, that.book)
                && Objects.equals(reader, that.reader)
                && Objects.equals(claimDate, that.claimDate)
                && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(book, reader, claimDate, returnDate);
    }

    @Override
    public String toString() {
        return "BookStorageReaderReportDto{" +
                "book='" + book + '\'' +
                ", reader='" + reader + '\'' +
                ", claimDate='" + claimDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                '}';
    }
}
