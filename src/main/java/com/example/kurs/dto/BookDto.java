package com.example.kurs.dto;

import java.time.LocalDate;
import java.util.Objects;

public class BookDto {
    private Integer id;
    private String name;
    private AuthorDto author;
    private PublishingHouseDto publishingHouse;
    private BookStorageDto bookStorage;
    private LocalDate releaseDate;
    private LocalDate registerDate;

    public BookDto(Integer id,
                   String name,
                   AuthorDto author,
                   PublishingHouseDto publishingHouse,
                   BookStorageDto bookStorage,
                   LocalDate releaseDate,
                   LocalDate registerDate) {

        this.id = id;
        this.name = name;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.bookStorage = bookStorage;
        this.releaseDate = releaseDate;
        this.registerDate = registerDate;
    }

    public BookDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public PublishingHouseDto getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouseDto publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public BookStorageDto getBookStorage() {
        return bookStorage;
    }

    public void setBookStorage(BookStorageDto bookStorage) {
        this.bookStorage = bookStorage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id)
                && Objects.equals(name, bookDto.name)
                && Objects.equals(author, bookDto.author)
                && Objects.equals(publishingHouse, bookDto.publishingHouse)
                && Objects.equals(bookStorage, bookDto.bookStorage)
                && Objects.equals(releaseDate, bookDto.releaseDate)
                && Objects.equals(registerDate, bookDto.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publishingHouse, bookStorage, releaseDate, registerDate);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", publishingHouse=" + publishingHouse +
                ", bookStorage=" + bookStorage +
                ", releaseDate=" + releaseDate +
                ", registerDate=" + registerDate +
                '}';
    }
}
