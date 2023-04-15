package com.example.kurs.dto.report;

import com.example.kurs.dto.fxml.BookFxmlDto;

import java.util.Objects;

public class BookReportDto {
    private String name;
    private String author;
    private String publishingHouse;
    private String location;
    private String section;
    private String releaseDate;
    private String registerDate;

    public BookReportDto(BookFxmlDto bookFxmlDto) {
        this.name = bookFxmlDto.getName();
        this.author = bookFxmlDto.getAuthor();
        this.publishingHouse = bookFxmlDto.getPublishingHouse();
        this.location = bookFxmlDto.getLocation();
        this.section = bookFxmlDto.getSection();
        this.releaseDate = bookFxmlDto.getReleaseDate();
        this.registerDate = bookFxmlDto.getRegisterDate();
    }

    public BookReportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookReportDto that = (BookReportDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(author, that.author)
                && Objects.equals(publishingHouse, that.publishingHouse)
                && Objects.equals(location, that.location)
                && Objects.equals(section, that.section)
                && Objects.equals(releaseDate, that.releaseDate)
                && Objects.equals(registerDate, that.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, publishingHouse, location, section, releaseDate, registerDate);
    }

    @Override
    public String toString() {
        return "BookReportDto{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", location='" + location + '\'' +
                ", section='" + section + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", registerDate='" + registerDate + '\'' +
                '}';
    }
}
