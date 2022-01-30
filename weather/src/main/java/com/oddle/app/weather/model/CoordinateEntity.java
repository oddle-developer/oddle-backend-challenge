package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "coordinate")
public class CoordinateEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coordinate_id")
    private int coordinate_id;
    private double longitude;
    private double latitude;


    @JsonProperty("longitude")
    public double getLongitude() {
        return longitude;
    }

    @JsonProperty("lon")
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("latitude")
    public double getLatitude() {
        return latitude;
    }

    public int getCoordinate_id() {
        return coordinate_id;
    }

    public void setCoordinate_id(int coordinate_id) {
        this.coordinate_id = coordinate_id;
    }

    @JsonProperty("lat")
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "CoordinateEntity{" +
                "coordinate_id=" + coordinate_id +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
