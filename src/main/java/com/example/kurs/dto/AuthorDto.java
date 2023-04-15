package com.example.kurs.dto;

import java.time.LocalDate;
import java.util.Objects;

public class AuthorDto {
    private Integer id;
    private String surname;
    private String name;
    private LocalDate birthday;
    private String shortInfo;

    public AuthorDto(Integer id, String surname, String name, LocalDate birthday, String shortInfo) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.shortInfo = shortInfo;
    }

    public AuthorDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id)
                && Objects.equals(surname, authorDto.surname)
                && Objects.equals(name, authorDto.name)
                && Objects.equals(birthday, authorDto.birthday)
                && Objects.equals(shortInfo, authorDto.shortInfo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, birthday, shortInfo);
    }

    @Override
    public String toString() {
        return surname + " " + name;
    }
}
