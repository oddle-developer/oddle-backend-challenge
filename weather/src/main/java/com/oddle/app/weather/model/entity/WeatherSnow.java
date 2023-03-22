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
@Table(name = "weather_snow")
@AttributeOverride(name = "id", column = @Column(name = "weather_snow_id", nullable = false))
public class WeatherSnow extends BaseEntity {

    @Column
    public Double snowOneHour;

    @Column
    public Double snowThreeHours;

}
