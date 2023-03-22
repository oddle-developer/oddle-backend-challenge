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
@Table(name = "weather_main")
@AttributeOverride(name = "id", column = @Column(name = "weather_main_id", nullable = false))
public class WeatherMain extends BaseEntity {

    @Column
    public Double temperature;

    @Column
    public Double feelsLike;

    @Column
    public Double temperatureMin;

    @Column
    public Double temperatureMax;

    @Column
    public Integer pressure;

    @Column
    public Integer humidity;

    @Column
    public Integer seaLevel;

    @Column
    public Integer groundLevel;

}
