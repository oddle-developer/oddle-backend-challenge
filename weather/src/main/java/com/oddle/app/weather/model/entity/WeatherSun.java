package com.oddle.app.weather.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather_sun")
@AttributeOverride(name = "id", column = @Column(name = "weather_sun_id", nullable = false))
public class WeatherSun extends BaseEntity {

    @Column
    public Integer type;

    @Column
    public String countryCode;

    @Column
    public String message;

    @Column
    public LocalDateTime sunRise;

    @Column
    public LocalDateTime sunSet;

}
