package com.oddle.app.weather.dto.openweather;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class ResponseOpenWeatherDTO implements Serializable {
    private CoordinateDTO coord;
    private List<WeatherDTO> weather;
    private String base;
    private MainDTO main;
    private float visibility;
    private WindDTO wind;
    private CloudsDTO clouds;
    private float dt;
    private SysDTO sys;
    private int timezone;
    private int id;
    private String name;
    private int cod;

    public CoordinateDTO getCoord() {
        return coord;
    }

    public void setCoord(CoordinateDTO coord) {
        this.coord = coord;
    }

    public List<WeatherDTO> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDTO> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainDTO getMain() {
        return main;
    }

    public void setMain(MainDTO main) {
        this.main = main;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public WindDTO getWind() {
        return wind;
    }

    public void setWind(WindDTO wind) {
        this.wind = wind;
    }

    public CloudsDTO getClouds() {
        return clouds;
    }

    public void setClouds(CloudsDTO clouds) {
        this.clouds = clouds;
    }

    public float getDt() {
        return dt;
    }

    public void setDt(float dt) {
        this.dt = dt;
    }

    public SysDTO getSys() {
        return sys;
    }

    public void setSys(SysDTO sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
