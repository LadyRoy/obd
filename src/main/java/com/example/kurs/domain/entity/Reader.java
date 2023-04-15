package com.example.kurs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Читатель")
public class Reader {
    @Id
    @Column(name = "Номер_билет")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Имя_читатель")
    private String firstName;

    @Column(name = "Фамилия_читатель")
    private String lastName;

    @Column(name = "Номер_телефона")
    private String phoneNumber;

    @Column(name = "Адрес")
    private String address;

    @Column(name = "Почта")
    private String mail;

    @OneToMany(mappedBy = "reader")
    private List<BookStorageReader> bookStorageReaders;

    public Reader(Integer id,
                  String firstName,
                  String lastName,
                  String phoneNumber,
                  String address,
                  String mail,
                  List<BookStorageReader> bookStorageReaders) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.mail = mail;
        this.bookStorageReaders = bookStorageReaders;
    }

    public Reader(String firstName,
                  String lastName,
                  String phoneNumber,
                  String address,
                  String mail,
                  List<BookStorageReader> bookStorageReaders) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.mail = mail;
        this.bookStorageReaders = bookStorageReaders;
    }

    public Reader() {
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

    public List<BookStorageReader> getBookStorageReaders() {
        return bookStorageReaders;
    }

    public void setBookStorageReaders(List<BookStorageReader> bookStorageReaders) {
        this.bookStorageReaders = bookStorageReaders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(id, reader.id)
                && Objects.equals(firstName, reader.firstName)
                && Objects.equals(lastName, reader.lastName)
                && Objects.equals(phoneNumber, reader.phoneNumber)
                && Objects.equals(address, reader.address)
                && Objects.equals(mail, reader.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, phoneNumber, address, mail);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
