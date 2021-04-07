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
    private String condition;

    @Basic
    private String description;

    @Column(name = "temp_avg", columnDefinition = "DECIMAL(19,4)")
    private BigDecimal tempAvg;

    @Column(name = "temp_min", columnDefinition = "DECIMAL(19,4)")
    private BigDecimal tempMin;

    @Column(name = "temp_max", columnDefinition = "DECIMAL(19,4)")
    private BigDecimal tempMax;

    @Basic
    private int humidity;

    @Basic
    private int visibility;

    @Basic
    @Column(name = "wind_speed", columnDefinition = "DOUBLE")
    private double windSpeed;
}
