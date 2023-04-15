package com.example.kurs.dto.report;

import com.example.kurs.dto.fxml.AuthorFxmlDto;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AuthorReportDto {
    private String surname;
    private String name;
    private String birthday;
    private String shortInfo;
    private String bookNames;

    public AuthorReportDto(AuthorFxmlDto authorFxmlDto) {
        this.surname = authorFxmlDto.getSurname();
        this.name = authorFxmlDto.getName();
        this.birthday = authorFxmlDto.getBirthday();
        this.shortInfo = authorFxmlDto.getShortInfo();
        this.bookNames = authorFxmlDto.getBookNames();
    }

    public AuthorReportDto() {
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getBookNames() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames = bookNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorReportDto that = (AuthorReportDto) o;
        return Objects.equals(surname, that.surname)
                && Objects.equals(name, that.name)
                && Objects.equals(birthday, that.birthday)
                && Objects.equals(shortInfo, that.shortInfo)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, birthday, shortInfo, bookNames);
    }

    @Override
    public String toString() {
        return "AuthorReportDto{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", shortInfo='" + shortInfo + '\'' +
                ", bookNames='" + bookNames + '\'' +
                '}';
    }
}
