package com.example.kurs.dto;

import java.time.LocalDate;
import java.util.Objects;

public class BookStorageReaderDto {
    private Integer id;
    private LocalDate claimDate;
    private LocalDate returnDate;
    private BookStorageDto bookStorage;
    private ReaderDto reader;
    private boolean debtor;

    public BookStorageReaderDto(Integer id,
                                LocalDate claimDate,
                                LocalDate returnDate,
                                BookStorageDto bookStorage,
                                ReaderDto reader,
                                boolean debtor) {

        this.id = id;
        this.claimDate = claimDate;
        this.returnDate = returnDate;
        this.bookStorage = bookStorage;
        this.reader = reader;
        this.debtor = debtor;
    }

    public BookStorageReaderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookStorageDto getBookStorage() {
        return bookStorage;
    }

    public void setBookStorage(BookStorageDto bookStorage) {
        this.bookStorage = bookStorage;
    }

    public ReaderDto getReader() {
        return reader;
    }

    public void setReader(ReaderDto reader) {
        this.reader = reader;
    }

    public boolean isDebtor() {
        return debtor;
    }

    public void setDebtor(boolean debtor) {
        this.debtor = debtor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStorageReaderDto that = (BookStorageReaderDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(claimDate, that.claimDate)
                && Objects.equals(returnDate, that.returnDate)
                && Objects.equals(bookStorage, that.bookStorage)
                && Objects.equals(reader, that.reader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, claimDate, returnDate, bookStorage, reader);
    }

    @Override
    public String toString() {
        return "BookReaderDto{" +
                "id=" + id +
                ", claimDate=" + claimDate +
                ", returnDate=" + returnDate +
                ", bookStorage=" + bookStorage +
                ", reader=" + reader +
                '}';
    }
}
