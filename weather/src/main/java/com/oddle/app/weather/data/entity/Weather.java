package com.oddle.app.weather.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Data
@EqualsAndHashCode(callSuper = true)
public class Weather extends OddleEntity {

    @Basic
    @Column(name = "weather_condition")
    private String condition;

    @Basic
    @Column(name = "condition_description")
    private String description;

    @Basic
    @Column(name = "temp_avg")
    private double tempAvg;

    @Basic
    @Column(name = "temp_min")
    private double tempMin;

    @Basic
    @Column(name = "temp_max")
    private double tempMax;

    @Basic
    @Column(name = "humidity")
    private int humidity;

    @Basic
    @Column(name = "visibility")
    private int visibility;

    @Basic
    @Column(name = "wind_Speed")
    private double windSpeed;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private City city;
}
