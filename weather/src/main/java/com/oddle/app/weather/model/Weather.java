package com.oddle.app.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Weather extends OddleEntity {

    @Basic
    private String weatherCondition;

    @Basic
    private String conditionDescription;

    @Basic
    @Column
    private double tempAvg;

    @Basic
    private double tempMin;

    @Basic
    private double tempMax;

    @Basic
    private int humidity;

    @Basic
    private int visibility;

    @Basic
    private double windSpeed;

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
}
