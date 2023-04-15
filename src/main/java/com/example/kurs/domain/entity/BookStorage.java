package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Экземпляр")
public class BookStorage {
    @Id
    @Column(name = "Код_раздел_книга")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "Код_книга")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "Код_место_хранение")
    private Storage storage;

    @Column(name = "Раздел")
    private String section;

    @OneToMany(mappedBy = "bookStorage")
    private List<BookStorageReader> bookStorageReaders;

    public BookStorage(Integer id,
                       Book book,
                       Storage storage,
                       String section,
                       List<BookStorageReader> bookStorageReaders) {

        this.id = id;
        this.book = book;
        this.storage = storage;
        this.section = section;
        this.bookStorageReaders = bookStorageReaders;
    }

    public BookStorage(Book book,
                       Storage storage,
                       String section,
                       List<BookStorageReader> bookStorageReaders) {

        this.book = book;
        this.storage = storage;
        this.section = section;
        this.bookStorageReaders = bookStorageReaders;
    }

    public BookStorage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
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
        BookStorage that = (BookStorage) o;
        return Objects.equals(id, that.id)
                && Objects.equals(section, that.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, section);
    }

    @Override
    public String toString() {
        return "BookStorage{" +
                "id=" + id +
                ", section='" + section + '\'' +
                '}';
    }
}
