package com.example.kurs.dto;

import java.util.Objects;

public class AuthorBookDto {
    private Integer id;
    private AuthorDto author;
    private BookDto book;

    public AuthorBookDto(Integer id, AuthorDto author, BookDto book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }

    public AuthorBookDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBookDto that = (AuthorBookDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(author, that.author)
                && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, book);
    }

    @Override
    public String toString() {
        return "AuthorBookDto{" +
                "id=" + id +
                ", author=" + author +
                ", book=" + book +
                '}';
    }
}
