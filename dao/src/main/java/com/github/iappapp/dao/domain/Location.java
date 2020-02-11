package com.github.iappapp.dao.domain;

import java.io.Serializable;
import java.util.Objects;

public class Location implements Serializable {
    private Long id;
    private String location;
    private String locationNo;


    public Location() {
    }

    public Location(Long id, String location, String locationNo) {
        this.id = id;
        this.location = location;
        this.locationNo = locationNo;
    }

    public String getLocationNo() {
        return locationNo;
    }

    public void setLocationNo(String locationNo) {
        this.locationNo = locationNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationNo='" + locationNo + '\'' +
                ", location='" + location + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location1 = (Location) o;
        return Objects.equals(getLocationNo(), location1.getLocationNo()) &&
                Objects.equals(getLocation(), location1.getLocation()) &&
                Objects.equals(getId(), location1.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getLocationNo(), getLocation(), getId());
    }
}
