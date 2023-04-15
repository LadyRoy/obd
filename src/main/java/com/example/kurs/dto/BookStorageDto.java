package com.example.kurs.dto;

import java.util.Objects;

public class BookStorageDto {
    private Integer id;
    private BookDto book;
    private StorageDto storage;
    private String section;

    public BookStorageDto(Integer id, BookDto book, StorageDto storage, String section) {
        this.id = id;
        this.book = book;
        this.storage = storage;
        this.section = section;
    }

    public BookStorageDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public StorageDto getStorage() {
        return storage;
    }

    public void setStorage(StorageDto storage) {
        this.storage = storage;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStorageDto that = (BookStorageDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(book, that.book)
                && Objects.equals(storage, that.storage)
                && Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, book, storage, section);
    }

    @Override
    public String toString() {
        return "BookStorageDto{" +
                "id=" + id +
                ", book=" + book +
                ", storage=" + storage +
                ", section='" + section + '\'' +
                '}';
    }
}
