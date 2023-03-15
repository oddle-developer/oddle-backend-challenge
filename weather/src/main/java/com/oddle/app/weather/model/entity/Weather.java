package com.oddle.app.weather.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather")
@AttributeOverride(name = "id", column = @Column(name = "weather_id", nullable = false))
public class Weather extends BaseEntity {

    @Column(name = "base", length = 200)
    private String base;

    @Column(name = "visibility")
    private Integer visibility;

    @Column(name = "date_time_calculation")
    private LocalDateTime dateTimeCalculation;

    @Column(name = "timezone")
    private Integer timezone;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "cod", length = 100)
    private String cod;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "weather_coordinates_id")
    private WeatherCoordinates weatherCoordinates;
}
