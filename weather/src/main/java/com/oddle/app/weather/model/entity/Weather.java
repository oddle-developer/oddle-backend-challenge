package com.oddle.app.weather.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "weather_coordinates_id")
    private WeatherCoordinates weatherCoordinates;

    @OneToOne
    @JoinColumn(name = "weather_main_id")
    private WeatherMain weatherMain;

    @OneToOne
    @JoinColumn(name = "weather_wind_id")
    private WeatherWind weatherWind;

    @OneToOne
    @JoinColumn(name = "weather_cloud_id")
    private WeatherCloud weatherCloud;

    @OneToOne
    @JoinColumn(name = "weather_rain_id")
    private WeatherRain weatherRain;

    @OneToOne
    @JoinColumn(name = "weather_snow_id")
    private WeatherSnow weatherSnow;

    @OneToOne
    @JoinColumn(name = "weather_sun_id")
    private WeatherSun weatherSun;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="weather_map_weather_code",
            joinColumns=@JoinColumn(name="weather_id"),
            inverseJoinColumns=@JoinColumn(name="weather_code_id"))
    private List<WeatherCode> codes = new ArrayList<>();
}
