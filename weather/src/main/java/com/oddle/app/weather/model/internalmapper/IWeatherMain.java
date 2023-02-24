package com.oddle.app.weather.model.internalmapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "weather_main")
@JsonInclude(value = Include.NON_NULL)
public class IWeatherMain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "WeatherID", referencedColumnName = "ID")
    @JsonIgnore
    private IWeatherSummary weather;

    @Column(name = "Temperature")
    private BigDecimal temperature;

    @Column(name = "FeelsLike")
    private BigDecimal feelsLike;

    @Column(name = "TempMin")
    private BigDecimal tempMin;

    @Column(name = "TempMax")
    private BigDecimal tempMax;

    @Column(name = "Pressure")
    private BigDecimal pressure;

    @Column(name = "Humidity")
    private BigDecimal humidity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IWeatherSummary getWeather() {
        return weather;
    }

    public void setWeather(IWeatherSummary weather) {
        this.weather = weather;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public BigDecimal getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(BigDecimal feelsLike) {
        this.feelsLike = feelsLike;
    }

    public BigDecimal getTempMin() {
        return tempMin;
    }

    public void setTempMin(BigDecimal tempMin) {
        this.tempMin = tempMin;
    }

    public BigDecimal getTempMax() {
        return tempMax;
    }

    public void setTempMax(BigDecimal tempMax) {
        this.tempMax = tempMax;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "WeatherMain [id=" + id + ", temperature=" + temperature + ", feelsLike=" + feelsLike
                + ", tempMin=" + tempMin + ", tempMax=" + tempMax + ", pressure=" + pressure + ", humidity=" + humidity
                + "]";
    }
}
