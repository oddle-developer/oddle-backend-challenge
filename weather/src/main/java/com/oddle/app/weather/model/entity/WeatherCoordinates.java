package com.oddle.app.weather.model.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "weather_coordinates")
@AttributeOverride(name = "id", column = @Column(name = "weather_coordinates_id", nullable = false))
public class WeatherCoordinates extends BaseEntity {

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;
}
