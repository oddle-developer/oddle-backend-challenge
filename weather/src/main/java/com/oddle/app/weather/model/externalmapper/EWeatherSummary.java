package com.oddle.app.weather.model.externalmapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class EWeatherSummary implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Coordinate coord;
    private List<EWeatherCondition> weather;
    private String base;
    private EWeatherMain main;
    private BigDecimal visibility;
    private EWeatherWind wind;
    private Clouds clouds;
    private Long dt;
    private Sys sys;
    private Long timezone;
    private Long id;
    private String name;
    private int cod;
    private String message;

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public List<EWeatherCondition> getWeather() {
        return weather;
    }

    public void setWeather(List<EWeatherCondition> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public EWeatherMain getMain() {
        return main;
    }

    public void setMain(EWeatherMain main) {
        this.main = main;
    }

    public EWeatherWind getWind() {
        return wind;
    }

    public void setWind(EWeatherWind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Long getTimezone() {
        return timezone;
    }

    public void setTimezone(Long timezone) {
        this.timezone = timezone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public BigDecimal getVisibility() {
        return visibility;
    }

    public void setVisibility(BigDecimal visibility) {
        this.visibility = visibility;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "WeatherSummary [coord=" + coord + ", weather=" + weather + ", base=" + base + ", main=" + main
                + ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", dt=" + dt + ", sys=" + sys
                + ", timezone=" + timezone + ", id=" + id + ", name=" + name + ", cod=" + cod + ", message=" + message + "]";
    }
}
