package com.oddle.app.weather.entity;

import com.oddle.app.weather.model.MainModel;
import com.oddle.app.weather.model.WeatherModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Data
@EqualsAndHashCode(callSuper = true)
public class Weather extends OddleEntity {

    @Transient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final MainModel mainModel = new MainModel();

    @Transient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private final WeatherModel weatherModel = new WeatherModel();

    @Basic
    @Column(name = "visibility")
    private double visibility;

    @Basic
    @Column(name = "wind_Speed")
    private double windSpeed;

    public void setCondition(String condition) {
        weatherModel.setMain(condition);
    }

    @Basic
    @Column(name = "weather_condition")
    @Access(AccessType.PROPERTY)
    public String getCondition() {
        return weatherModel.getMain();
    }

    public void setDescription(String description) {
        weatherModel.setDescription(description);
    }

    @Basic
    @Column(name = "condition_description")
    @Access(AccessType.PROPERTY)
    public String getDescription() {
        return weatherModel.getDescription();
    }

    public void setTempAvg(double avgTemp) {
        mainModel.setTemp(avgTemp);
    }

    @Basic
    @Column(name = "temp_avg")
    @Access(AccessType.PROPERTY)
    public double getTempAvg() {
        return mainModel.getTemp();
    }

    public void setTempMin(double tempMin) {
        mainModel.setTempMin(tempMin);
    }

    @Basic
    @Column(name = "temp_min")
    @Access(AccessType.PROPERTY)
    public double getTempMin() {
        return mainModel.getTempMin();
    }

    public void setTempMax(double tempMax) {
        mainModel.setTempMax(tempMax);
    }

    @Basic
    @Column(name = "temp_max")
    @Access(AccessType.PROPERTY)
    public double getTempMax() {
        return mainModel.getTempMax();
    }

    public void setHumidity(int humidity) {
        mainModel.setHumidity(humidity);
    }

    @Basic
    @Column(name = "humidity")
    @Access(AccessType.PROPERTY)
    public int getHumidity() {
        return mainModel.getHumidity();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private City city;
}
