package com.example.kurs.dto.fxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class BookFxmlDto {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty author;
    private SimpleStringProperty publishingHouse;
    private SimpleStringProperty location;
    private SimpleStringProperty section;
    private SimpleStringProperty releaseDate;
    private SimpleStringProperty registerDate;

    public BookFxmlDto(SimpleIntegerProperty id,
                       SimpleStringProperty name,
                       SimpleStringProperty author,
                       SimpleStringProperty publishingHouse,
                       SimpleStringProperty location,
                       SimpleStringProperty section,
                       SimpleStringProperty releaseDate,
                       SimpleStringProperty registerDate) {

        this.id = id;
        this.name = name;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.location = location;
        this.section = section;
        this.releaseDate = releaseDate;
        this.registerDate = registerDate;
    }

    public BookFxmlDto() {
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAuthor() {
        return author.get();
    }

    public SimpleStringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublishingHouse() {
        return publishingHouse.get();
    }

    public SimpleStringProperty publishingHouseProperty() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse.set(publishingHouse);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getSection() {
        return section.get();
    }

    public SimpleStringProperty sectionProperty() {
        return section;
    }

    public void setSection(String section) {
        this.section.set(section);
    }

    public String getReleaseDate() {
        return releaseDate.get();
    }

    public SimpleStringProperty releaseDateProperty() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate.set(releaseDate);
    }

    public String getRegisterDate() {
        return registerDate.get();
    }

    public SimpleStringProperty registerDateProperty() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate.set(registerDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookFxmlDto that = (BookFxmlDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(author, that.author)
                && Objects.equals(publishingHouse, that.publishingHouse)
                && Objects.equals(location, that.location)
                && Objects.equals(section, that.section)
                && Objects.equals(releaseDate, that.releaseDate)
                && Objects.equals(registerDate, that.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, author, publishingHouse, location, section, releaseDate, registerDate);
    }

    @Override
    public String toString() {
        return "BookFxmlDto{" +
                "id=" + id +
                ", name=" + name +
                ", author=" + author +
                ", publishingHouse=" + publishingHouse +
                ", location=" + location +
                ", section=" + section +
                ", releaseDate=" + releaseDate +
                ", registerDate=" + registerDate +
                '}';
    }
}
