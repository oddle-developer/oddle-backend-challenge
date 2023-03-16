package com.oddle.app.weather.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather_coordinates")
@AttributeOverride(name = "id", column = @Column(name = "weather_coordinates_id", nullable = false))
public class WeatherCoordinates extends BaseEntity {

    @Column(name = "latitude")
    public Double latitude;

    @Column(name = "longitude")
    public Double longitude;
}
