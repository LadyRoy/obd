package com.example.kurs.dto;

import java.util.List;
import java.util.Objects;

public class ReaderDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String mail;
    private List<BookDto> books;

    public ReaderDto(Integer id,
                     String firstName,
                     String lastName,
                     String phoneNumber,
                     String address,
                     String mail,
                     List<BookDto> books) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.mail = mail;
        this.books = books;
    }

    public ReaderDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReaderDto readerDto = (ReaderDto) o;
        return Objects.equals(id, readerDto.id)
                && Objects.equals(firstName, readerDto.firstName)
                && Objects.equals(lastName, readerDto.lastName)
                && Objects.equals(phoneNumber, readerDto.phoneNumber)
                && Objects.equals(address, readerDto.address)
                && Objects.equals(mail, readerDto.mail)
                && Objects.equals(books, readerDto.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, address, mail, books);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }
}
