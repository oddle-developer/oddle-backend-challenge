package com.oddle.app.weather.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather")
@AttributeOverride(name = "id", column = @Column(name = "weather_id", nullable = false))
public class Weather extends BaseEntity {

    @Column(length = 200)
    private String base;

    @Column
    private Integer visibility;

    @Column(name = "date_time_calculation")
    private Date dateTimeCalculation;

    @Column
    private Integer timezone;

    @Column(length = 200)
    private String name;

    @Column(length = 100)
    private Integer cod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_coordinates_id")
    private WeatherCoordinates weatherCoordinates;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_main_id")
    private WeatherMain weatherMain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_wind_id")
    private WeatherWind weatherWind;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_cloud_id")
    private WeatherCloud weatherCloud;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_rain_id")
    private WeatherRain weatherRain;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_snow_id")
    private WeatherSnow weatherSnow;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_sun_id")
    private WeatherSun weatherSun;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "weather_map_weather_code",
            joinColumns = @JoinColumn(name = "weather_id"),
            inverseJoinColumns = @JoinColumn(name = "weather_code_id"))
    private List<WeatherCode> codes = new ArrayList<>();
}
