package com.oddle.app.weather.model.internalmapper;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "weather_condition")
@JsonInclude(value = Include.NON_NULL)
public class IWeatherCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonIgnore
    private Long id;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "WeatherID", referencedColumnName = "ID")
    @JsonIgnore
    private IWeatherSummary weather;

    @Column(name = "Code")
    private int code;

    @Column(name = "Main")
    private String main;

    @Column(name = "Description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public IWeatherSummary getWeather() {
        return weather;
    }

    public void setWeather(IWeatherSummary weather) {
        this.weather = weather;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "IWeatherCondition [id=" + id + ", code=" + code + ", main=" + main
                + ", description=" + description + "]";
    }
}
