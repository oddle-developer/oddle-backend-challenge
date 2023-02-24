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
@Table(name = "weather_wind")
@JsonInclude(value = Include.NON_NULL)
public class IWeatherWind {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "WeatherID", referencedColumnName = "ID")
    @JsonIgnore
    private IWeatherSummary weather;

    @Column(name = "Speed")
    private BigDecimal speed;

    @Column(name = "Direction")
    private BigDecimal direction;

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

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getDirection() {
        return direction;
    }

    public void setDirection(BigDecimal direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "WeatherWind [id=" + id + ", speed=" + speed + ", direction="
                + direction + "]";
    }
}
