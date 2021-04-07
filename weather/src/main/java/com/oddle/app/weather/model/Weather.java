package com.oddle.app.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Weather extends OddleEntity {

    @Basic
    private String weatherCondition;

    @Basic
    private String conditionDescription;

    @Basic
    @Column(precision = 19, scale = 4)
    private BigDecimal tempAvg;

    @Basic
    @Column(precision = 19, scale = 4)
    private BigDecimal tempMin;

    @Basic
    @Column(precision = 19, scale = 4)
    private BigDecimal tempMax;

    @Basic
    private int humidity;

    @Basic
    private int visibility;

    @Basic
    private double windSpeed;
}
