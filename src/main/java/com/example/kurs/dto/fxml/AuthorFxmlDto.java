package com.example.kurs.dto.fxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class AuthorFxmlDto {
    private SimpleIntegerProperty id;
    private SimpleStringProperty surname;
    private SimpleStringProperty name;
    private SimpleStringProperty birthday;
    private SimpleStringProperty shortInfo;
    private SimpleStringProperty bookNames;

    public AuthorFxmlDto(SimpleIntegerProperty id,
                         SimpleStringProperty surname,
                         SimpleStringProperty name,
                         SimpleStringProperty birthday,
                         SimpleStringProperty shortInfo,
                         SimpleStringProperty bookNames) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.birthday = birthday;
        this.shortInfo = shortInfo;
        this.bookNames = bookNames;
    }

    public AuthorFxmlDto() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getBirthday() {
        return birthday.get();
    }

    public SimpleStringProperty birthdayProperty() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday.set(birthday);
    }

    public String getShortInfo() {
        return shortInfo.get();
    }

    public SimpleStringProperty shortInfoProperty() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo.set(shortInfo);
    }

    public String getBookNames() {
        return bookNames.get();
    }

    public SimpleStringProperty bookNamesProperty() {
        return bookNames;
    }

    public void setBookNames(String bookNames) {
        this.bookNames.set(bookNames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorFxmlDto that = (AuthorFxmlDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(surname, that.surname)
                && Objects.equals(name, that.name)
                && Objects.equals(birthday, that.birthday)
                && Objects.equals(shortInfo, that.shortInfo)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, birthday, shortInfo, bookNames);
    }

    @Override
    public String toString() {
        return "AuthorFxmlDto{" +
                "id=" + id +
                ", surname=" + surname +
                ", name=" + name +
                ", birthday=" + birthday +
                ", shortInfo=" + shortInfo +
                ", bookNames=" + bookNames +
                '}';
    }
}
