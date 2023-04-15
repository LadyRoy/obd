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
@Table(name = "Место_хранения")
public class Storage {
    @Id
    @Column(name = "Код_место_хранения")
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "Местоположение_библиотека")
    private String libraryLocation;

    @OneToMany(mappedBy = "storage")
    private List<BookStorage> bookStorages;

    public Storage(Integer id,
                   String libraryLocation,
                   List<BookStorage> bookStorages) {
        this.id = id;
        this.libraryLocation = libraryLocation;
        this.bookStorages = bookStorages;
    }

    public Storage(String libraryLocation, List<BookStorage> bookStorages) {
        this.libraryLocation = libraryLocation;
        this.bookStorages = bookStorages;
    }

    public Storage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibraryLocation() {
        return libraryLocation;
    }

    public void setLibraryLocation(String libraryLocation) {
        this.libraryLocation = libraryLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return Objects.equals(id, storage.id)
                && Objects.equals(libraryLocation, storage.libraryLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libraryLocation);
    }

    @Override
    public String toString() {
        return "Storage{" +
                "id=" + id +
                ", libraryLocation='" + libraryLocation + '\'' +
                '}';
    }
}
