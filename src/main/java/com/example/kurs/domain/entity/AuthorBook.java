package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Автор_книга")
public class AuthorBook {
    @Id
    @Column(name = "Код_автор_книга")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Код_автор")
    private Author author;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Код_книга")
    private Book book;

    public AuthorBook(Integer id, Author author, Book book) {
        this.id = id;
        this.author = author;
        this.book = book;
    }

    public AuthorBook(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    public AuthorBook() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBook that = (AuthorBook) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AuthorBook{" +
                "id=" + id +
                ", author=" + author +
                '}';
    }
}
