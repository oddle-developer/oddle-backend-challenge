package com.oddle.app.weather.model.internalmapper;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "city")
@JsonInclude(value = Include.NON_NULL)
public class City {
    @Id
    @Column(name = "Name")
    private String name;

    @Column(name = "Country")
    private String country;

    @Column(name = "Longitude")
    private BigDecimal longitude;

    @Column(name = "Latitude")
    private BigDecimal latitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "City [name=" + name + ", country=" + country + ", longitude=" + longitude + ", latitude="
                + latitude + "]";
    }
}
