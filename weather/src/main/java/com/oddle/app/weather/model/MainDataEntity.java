package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "main_data")
public class MainDataEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "main_data_id")
    private Integer mainDataId;

    private float temperature;
    private float feelsLike;
    private int pressure;
    private short humidity;
    private float minTemperature;
    private float maxTemperature;
    private int seaLevelPressure;
    private int groundLevelPressure;

    @JsonProperty("temperature")
    public float getTemperature() {
        return temperature;
    }

    @JsonProperty("temp")
    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    @JsonProperty("feelsLike")
    public float getFeelsLike() {
        return feelsLike;
    }

    @JsonProperty("feels_like")
    public void setFeelsLike(float feelsLike) {
        this.feelsLike = feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public short getHumidity() {
        return humidity;
    }

    public void setHumidity(short humidity) {
        this.humidity = humidity;
    }

    @JsonProperty("minTemperature")
    public float getMinTemperature() {
        return minTemperature;
    }

    @JsonProperty("temp_min")
    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    @JsonProperty("maxTemperature")
    public float getMaxTemperature() {
        return maxTemperature;
    }

    @JsonProperty("temp_max")
    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @JsonProperty("seaLevelPressure")
    public int getSeaLevelPressure() {
        return seaLevelPressure;
    }

    @JsonProperty("sea_level")
    public void setSeaLevelPressure(int seaLevelPressure) {
        this.seaLevelPressure = seaLevelPressure;
    }

    @JsonProperty("groundLevelPressure")
    public int getGroundLevelPressure() {
        return groundLevelPressure;
    }

    @JsonProperty("grnd_level")
    public void setGroundLevelPressure(int groundLevelPressure) {
        this.groundLevelPressure = groundLevelPressure;
    }

    public Integer getMainDataId() {
        return mainDataId;
    }

    public void setMainDataId(Integer mainDataId) {
        this.mainDataId = mainDataId;
    }

    @Override
    public String toString() {
        return "MainDataEntity{" +
                "mainDataId=" + mainDataId +
                ", temperature=" + temperature +
                ", feelsLike=" + feelsLike +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", seaLevelPressure=" + seaLevelPressure +
                ", groundLevelPressure=" + groundLevelPressure +
                '}';
    }
}
