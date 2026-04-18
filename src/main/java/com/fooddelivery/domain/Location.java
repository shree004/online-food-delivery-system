package com.fooddelivery.domain;

import java.util.Objects;

public class Location {
    private final String area;
    private final String city;

    public Location(String area, String city) {
        this.area = area;
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location location)) return false;
        return Objects.equals(area, location.area) && Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area, city);
    }

    @Override
    public String toString() {
        return area + ", " + city;
    }
}
