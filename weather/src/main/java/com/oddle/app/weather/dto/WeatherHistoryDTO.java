package com.oddle.app.weather.dto;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Date;

public class WeatherHistoryDTO implements Serializable {
    private String id;
    private double lattitude;
    private double longitude;
    private String cityCode;
    private String cityName;
    private String mainWeather;
    private String detailWeather;
    private String weatherIcon;
    private Date dateAdded;
    private Date dateModified;
    private boolean isDeleted;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(String mainWeather) {
        this.mainWeather = mainWeather;
    }

    public String getDetailWeather() {
        return detailWeather;
    }

    public void setDetailWeather(String detailWeather) {
        this.detailWeather = detailWeather;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateModified() {
        return dateModified;
    }

    public void setDateModified(Date dateModified) {
        this.dateModified = dateModified;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
