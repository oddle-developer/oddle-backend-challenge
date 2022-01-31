package com.oddle.app.weather.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weather_data")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_data_id")
    private int weatherDataId;

    private String base;

    private double temp;

    private int pressure;

    private float feelsLike;

    private int humidity;

    private double tempMin;

    private double tempMax;

    private int visibility;

    @ManyToOne()
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToMany
    @JoinTable(name = "weather_data_mapping", joinColumns = @JoinColumn(name = "weather_data_id"), inverseJoinColumns = @JoinColumn(name = "weather_id"))
    private List<Weather> weathers;

    @Override
    public String toString() {
        return "WeatherData{" +
                "weatherDataId=" + weatherDataId +
                ", base='" + base + '\'' +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", feelsLike=" + feelsLike +
                ", humidity=" + humidity +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", visibility=" + visibility +
                ", city=" + city +
                '}';
    }

    public int getWeatherDataId() {
        return weatherDataId;
    }

    public void setWeatherDataId(int weatherDataId) {
        this.weatherDataId = weatherDataId;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
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

}
