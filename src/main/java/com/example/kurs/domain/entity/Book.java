package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Книга")
public class Book {
    @Id
    @Column(name = "Код_книга")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Название")
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "Код_издательства")
    private PublishingHouse publishingHouse;

    @Column(name = "Дата_выпуска")
    private LocalDate releaseDate;

    @Column(name = "Дата_регистрации")
    private LocalDate registerDate;

    @OneToMany(mappedBy = "book")
    private List<AuthorBook> authorBooks;

    @OneToMany(mappedBy = "book")
    private List<BookStorage> bookStorages;

    public Book(Integer id,
                String name,
                PublishingHouse publishingHouse,
                LocalDate releaseDate,
                LocalDate registerDate,
                List<AuthorBook> authorBooks,
                List<BookStorage> bookStorages) {

        this.id = id;
        this.name = name;
        this.publishingHouse = publishingHouse;
        this.releaseDate = releaseDate;
        this.registerDate = registerDate;
        this.authorBooks = authorBooks;
        this.bookStorages = bookStorages;
    }

    public Book(String name,
                PublishingHouse publishingHouse,
                LocalDate releaseDate,
                LocalDate registerDate,
                List<AuthorBook> authorBooks,
                List<BookStorage> bookStorages) {

        this.name = name;
        this.publishingHouse = publishingHouse;
        this.releaseDate = releaseDate;
        this.registerDate = registerDate;
        this.authorBooks = authorBooks;
        this.bookStorages = bookStorages;
    }

    public Book() {
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

    public PublishingHouse getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(PublishingHouse publishingHouse) {
        this.publishingHouse = publishingHouse;
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

    public List<AuthorBook> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<AuthorBook> authorBooks) {
        this.authorBooks = authorBooks;
    }

    public List<BookStorage> getBookStorages() {
        return bookStorages;
    }

    public void setBookStorages(List<BookStorage> bookStorages) {
        this.bookStorages = bookStorages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(name, book.name)
                && Objects.equals(releaseDate, book.releaseDate)
                && Objects.equals(registerDate, book.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, releaseDate, registerDate);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", registerDate=" + registerDate +
                '}';
    }
}
