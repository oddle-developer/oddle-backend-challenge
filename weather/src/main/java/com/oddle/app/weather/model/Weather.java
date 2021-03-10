package com.oddle.app.weather.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Weather implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String cityName;
    private Double temperature;
    private String mainWeather;
    private String descWeather;
    private LocalDateTime accessedAt;

    // constructor - default
    public Weather() {}

    // constructor - param
    public Weather(
            String cityName,
            Double temperature,
            String mainWeather,
            String descWeather
    ) {
        this.cityName = cityName;
        this.temperature = temperature;
        this.mainWeather = mainWeather;
        this.descWeather = descWeather;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getCityName() { return cityName; }
    public Double getTemperature() { return temperature; }
    public String getMainWeather() { return mainWeather; }
    public String getDescWeather() { return descWeather; }
    public LocalDateTime getAccessedAt() { return accessedAt; }
    public void setId(Long id) { this.id = id; }
    public void setCityName(String cityName) { this.cityName = cityName; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public void setMainWeather(String mainWeather) { this.mainWeather = mainWeather; }
    public void setDescWeather(String descWeather) { this.descWeather = descWeather; }
    public void setAccessedAt(LocalDateTime accessedAt) {
        this.accessedAt = accessedAt;
    }
}
