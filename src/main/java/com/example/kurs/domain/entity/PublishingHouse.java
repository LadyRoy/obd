package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Издательство")
public class PublishingHouse {
    @Id
    @Column(name = "Код_издательство")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Наименование")
    private String name;

    @Column(name = "Местоположение")
    private String location;

    @Column(name = "`Кол-во_выпущено`")
    private Integer releasedAmount;

    @OneToMany(mappedBy = "publishingHouse")
    private List<Book> releasedBooks;

    public PublishingHouse(Integer id,
                           String name,
                           String location,
                           Integer releasedAmount,
                           List<Book> releasedBooks) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.releasedAmount = releasedAmount;
        this.releasedBooks = releasedBooks;
    }

    public PublishingHouse(String name,
                           String location,
                           Integer releasedAmount,
                           List<Book> releasedBooks) {

        this.name = name;
        this.location = location;
        this.releasedAmount = releasedAmount;
        this.releasedBooks = releasedBooks;
    }

    public PublishingHouse() {
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getReleasedAmount() {
        return releasedAmount;
    }

    public void setReleasedAmount(Integer releasedAmount) {
        this.releasedAmount = releasedAmount;
    }

    public List<Book> getReleasedBooks() {
        return releasedBooks;
    }

    public void setReleasedBooks(List<Book> releasedBooks) {
        this.releasedBooks = releasedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouse that = (PublishingHouse) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(location, that.location)
                && Objects.equals(releasedAmount, that.releasedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, releasedAmount);
    }

    @Override
    public String toString() {
        return "PublishingHouse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", releasedAmount=" + releasedAmount +
                '}';
    }
}
