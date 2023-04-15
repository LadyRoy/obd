package com.example.kurs.dto.report;

import com.example.kurs.dto.fxml.PublishingHouseFxmlDto;

import java.util.Objects;

public class PublishingHouseReportDto {
    private String name;
    private String location;
    private String releasedAmount;
    private String bookNames;

    public PublishingHouseReportDto(PublishingHouseFxmlDto publishingHouseFxmlDto) {
        this.name = publishingHouseFxmlDto.getName();
        this.location = publishingHouseFxmlDto.getLocation();
        this.releasedAmount = String.valueOf(publishingHouseFxmlDto.getReleasedAmount());
        this.bookNames = publishingHouseFxmlDto.getBookNames();
    }

    public PublishingHouseReportDto() {
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

    public String getReleasedAmount() {
        return releasedAmount;
    }

    public void setReleasedAmount(String releasedAmount) {
        this.releasedAmount = releasedAmount;
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
        PublishingHouseReportDto that = (PublishingHouseReportDto) o;
        return Objects.equals(name, that.name)
                && Objects.equals(location, that.location)
                && Objects.equals(releasedAmount, that.releasedAmount)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, location, releasedAmount, bookNames);
    }

    @Override
    public String toString() {
        return "PublishingHouseReportDto{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", releasedAmount='" + releasedAmount + '\'' +
                ", bookNames='" + bookNames + '\'' +
                '}';
    }
}
