package com.example.kurs.domain.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Выдача_книга")
public class BookStorageReader {
    @Id
    @Column(name = "Код_выдача_книга")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Дата_выдача")
    private LocalDate claimDate;

    @Column(name = "Дата_возврат")
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "Код_раздел_книга")
    private BookStorage bookStorage;

    @ManyToOne
    @JoinColumn(name = "Номер_билет")
    private Reader reader;

    @Column(name = "Должник")
    @Type(type = "yes_no")
    private boolean debtor;

    public BookStorageReader(Integer id,
                             LocalDate claimDate,
                             LocalDate returnDate,
                             BookStorage bookStorage,
                             Reader reader,
                             boolean debtor) {

        this.id = id;
        this.claimDate = claimDate;
        this.returnDate = returnDate;
        this.bookStorage = bookStorage;
        this.reader = reader;
        this.debtor = debtor;
    }

    public BookStorageReader(LocalDate claimDate,
                             LocalDate returnDate,
                             BookStorage bookStorage,
                             Reader reader,
                             boolean debtor) {

        this.claimDate = claimDate;
        this.returnDate = returnDate;
        this.bookStorage = bookStorage;
        this.reader = reader;
        this.debtor = debtor;
    }

    public BookStorageReader() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookStorage getBookStorage() {
        return bookStorage;
    }

    public void setBookStorage(BookStorage bookStorage) {
        this.bookStorage = bookStorage;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public boolean isDebtor() {
        return debtor;
    }

    public void setDebtor(boolean debtor) {
        this.debtor = debtor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookStorageReader that = (BookStorageReader) o;
        return debtor == that.debtor
                && Objects.equals(id, that.id)
                && Objects.equals(claimDate, that.claimDate)
                && Objects.equals(returnDate, that.returnDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, claimDate, returnDate, debtor);
    }

    @Override
    public String toString() {
        return "BookStorageReader{" +
                "id=" + id +
                ", claimDate=" + claimDate +
                ", returnDate=" + returnDate +
                ", debtor=" + debtor +
                '}';
    }
}
