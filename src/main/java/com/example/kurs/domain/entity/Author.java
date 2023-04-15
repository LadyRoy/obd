package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Автор")
public class Author {
    @Id
    @Column(name = "Код_автор")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Фамилия_автор")
    private String surname;

    @Column(name = "Имя_автор")
    private String name;

    @Column(name = "Дата_рождения")
    private LocalDate birthday;

    @Column(name = "Краткая_биография")
    private String shortInfo;

    @OneToMany(mappedBy = "author")
    private List<AuthorBook> authorBooks;

    public Author(Integer id,
                  String surname,
                  String name,
                  LocalDate birthday,
                  String shortInfo,
                  List<AuthorBook> authorBooks) {

        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.shortInfo = shortInfo;
        this.authorBooks = authorBooks;
    }

    public Author(String surname,
                  String name,
                  LocalDate birthday,
                  String shortInfo,
                  List<AuthorBook> authorBooks) {

        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.shortInfo = shortInfo;
        this.authorBooks = authorBooks;
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer code) {
        this.id = code;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public List<AuthorBook> getAuthorBooks() {
        return authorBooks;
    }

    public void setAuthorBooks(List<AuthorBook> authorBooks) {
        this.authorBooks = authorBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id)
                && Objects.equals(surname, author.surname)
                && Objects.equals(name, author.name)
                && Objects.equals(birthday, author.birthday)
                && Objects.equals(shortInfo, author.shortInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, birthday, shortInfo);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", shortInfo='" + shortInfo + '\'' +
                '}';
    }
}
