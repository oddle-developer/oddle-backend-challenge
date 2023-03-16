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
@Table(name = "weather_rain")
@AttributeOverride(name = "id", column = @Column(name = "weather_rain_id", nullable = false))
public class WeatherRain extends BaseEntity {

    @Column
    public Double rainOneHour;

    @Column
    public Double rainThreeHours;

}
