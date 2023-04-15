package com.example.kurs.dto.report;

import com.example.kurs.dto.fxml.ReaderFxmlDto;

import java.util.Objects;

public class ReaderReportDto {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String mail;
    private String bookNames;

    public ReaderReportDto(ReaderFxmlDto readerFxmlDto) {
        this.firstName = readerFxmlDto.getFirstName();
        this.lastName = readerFxmlDto.getLastName();
        this.phoneNumber = readerFxmlDto.getPhoneNumber();
        this.address = readerFxmlDto.getPhoneNumber();
        this.mail = readerFxmlDto.getMail();
        this.bookNames = readerFxmlDto.getBookNames();
    }

    public ReaderReportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
        ReaderReportDto that = (ReaderReportDto) o;
        return Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(phoneNumber, that.phoneNumber)
                && Objects.equals(address, that.address)
                && Objects.equals(mail, that.mail)
                && Objects.equals(bookNames, that.bookNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, address, mail, bookNames);
    }

    @Override
    public String toString() {
        return "ReaderReportDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", bookNames='" + bookNames + '\'' +
                '}';
    }
}
