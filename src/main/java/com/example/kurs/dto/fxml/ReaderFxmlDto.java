package com.example.kurs.dto.fxml;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Objects;

public class ReaderFxmlDto {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private SimpleStringProperty mail;
    private SimpleStringProperty bookNames;

    public ReaderFxmlDto(SimpleIntegerProperty id,
                         SimpleStringProperty firstName,
                         SimpleStringProperty lastName,
                         SimpleStringProperty phoneNumber,
                         SimpleStringProperty address,
                         SimpleStringProperty mail,
                         SimpleStringProperty bookNames) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.mail = mail;
        this.bookNames = bookNames;
    }

    public ReaderFxmlDto() {
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

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public SimpleStringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getMail() {
        return mail.get();
    }

    public SimpleStringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
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
        ReaderFxmlDto that = (ReaderFxmlDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(address, that.address)
                && Objects.equals(mail, that.mail)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, address, mail, bookNames);
    }

    @Override
    public String toString() {
        return "ReaderFxmlDto{" +
                "id=" + id +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", phoneNumber=" + phoneNumber +
                ", address=" + address +
                ", mail=" + mail +
                ", bookNames=" + bookNames +
                '}';
    }
}
