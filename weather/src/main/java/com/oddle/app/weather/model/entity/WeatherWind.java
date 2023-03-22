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
@Table(name = "weather_wind")
@AttributeOverride(name = "id", column = @Column(name = "weather_wind_id", nullable = false))
public class WeatherWind extends BaseEntity {

    @Column
    public Double speed;

    @Column
    public Double gust;

    @Column
    public Double direction;

}
