package com.example.kurs.dto.fxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class PublishingHouseFxmlDto {
    private SimpleIntegerProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty location;
    private SimpleIntegerProperty releasedAmount;
    private SimpleStringProperty bookNames;

    public PublishingHouseFxmlDto(SimpleIntegerProperty id,
                                  SimpleStringProperty name,
                                  SimpleStringProperty location,
                                  SimpleIntegerProperty releasedAmount,
                                  SimpleStringProperty bookNames) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.releasedAmount = releasedAmount;
        this.bookNames = bookNames;
    }

    public PublishingHouseFxmlDto() {
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

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public int getReleasedAmount() {
        return releasedAmount.get();
    }

    public SimpleIntegerProperty releasedAmountProperty() {
        return releasedAmount;
    }

    public void setReleasedAmount(int releasedAmount) {
        this.releasedAmount.set(releasedAmount);
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
        PublishingHouseFxmlDto that = (PublishingHouseFxmlDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(location, that.location)
                && Objects.equals(releasedAmount, that.releasedAmount)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, releasedAmount, bookNames);
    }

    @Override
    public String toString() {
        return "PublishingHouseFxmlDto{" +
                "id=" + id +
                ", name=" + name +
                ", location=" + location +
                ", releasedAmount=" + releasedAmount +
                ", bookNames=" + bookNames +
                '}';
    }
}
