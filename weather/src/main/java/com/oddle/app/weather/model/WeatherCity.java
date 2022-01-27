package com.oddle.app.weather.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "city_weather")
public class WeatherCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_weather_id")
    private Long cityWeatherId;

    private String base;

    private double temperature;

    private int pressure;

    private int humidity;

    private double tempMin;

    private double tempMax;

    @Column(nullable = false)
    private LocalDateTime weatherDate;

    private int visibility;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "city_id", insertable = false, updatable = false)
    private Long cityId;

    @ManyToMany
    @JoinTable(name = "city_weather_mapping", joinColumns = @JoinColumn(name = "city_weather_id"), inverseJoinColumns = @JoinColumn(name = "weather_id"))
    private List<Weather> weathers;

    @PrePersist
    public void persistWeatherDate() {
        this.weatherDate = LocalDateTime.now();
    }

    @PreUpdate
    public void updateWeatherDate() {
        this.weatherDate = LocalDateTime.now();
    }

    public Long getCityWeatherId() {
        return cityWeatherId;
    }

    public void setCityWeatherId(Long cityWeatherId) {
        this.cityWeatherId = cityWeatherId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public LocalDateTime getWeatherDate() {
        return weatherDate;
    }

    public void setWeatherDate(LocalDateTime weatherDate) {
        this.weatherDate = weatherDate;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
