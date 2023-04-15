package com.example.kurs.dto;

import java.util.Objects;

public class StorageDto {
    private Integer id;
    private String libraryLocation;

    public StorageDto(Integer id, String libraryLocation) {
        this.id = id;
        this.libraryLocation = libraryLocation;
    }

    public StorageDto() {
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
        StorageDto that = (StorageDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(libraryLocation, that.libraryLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, libraryLocation);
    }

    @Override
    public String toString() {
        return "StorageDto{" +
                "id=" + id +
                ", libraryLocation='" + libraryLocation + '\'' +
                '}';
    }
}
