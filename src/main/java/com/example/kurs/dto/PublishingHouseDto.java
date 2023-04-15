package com.example.kurs.dto;

import java.util.Objects;

public class PublishingHouseDto {
    private Integer id;
    private String name;
    private String location;
    private Integer releasedAmount;

    public PublishingHouseDto(Integer id, String name, String location, Integer releasedAmount) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.releasedAmount = releasedAmount;
    }

    public PublishingHouseDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getReleasedAmount() {
        return releasedAmount;
    }

    public void setReleasedAmount(Integer releasedAmount) {
        this.releasedAmount = releasedAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublishingHouseDto that = (PublishingHouseDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(name, that.name)
                && Objects.equals(location, that.location)
                && Objects.equals(releasedAmount, that.releasedAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, releasedAmount);
    }

    @Override
    public String toString() {
        return name + ", " + location;
    }
}
