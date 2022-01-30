package com.oddle.app.weather.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weather_data")
public class WeatherDataEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_data_id")
    private Integer WeatherDataId;

    private int cityId;
    private String cityName;
    private int cod;
    private int timeZone;

    @OneToOne
    @JoinColumn(name = "coordinate_id", nullable = false)
    private CoordinateEntity coordinate;

    private String base;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private SystemDataEntity systemData;

    private long calculationDateTime;
    private int visibility;

    @OneToOne
    @JoinColumn(name = "main_data_id", nullable = false)
    private MainDataEntity mainData;

    @OneToOne
    @JoinColumn(name = "wind_id", nullable = false)
    private WindEntity wind;

    @OneToOne
    @JoinColumn(name = "rain_id", nullable = false)
    private RainEntity rain;

    @OneToOne
    @JoinColumn(name = "snow_id", nullable = false)
    private SnowEntity snow;

    @OneToOne
    @JoinColumn(name = "cloud_id", nullable = false)
    private CloudEntity clouds;

    @OneToMany(mappedBy = "weatherData", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<WeatherEntity> weather;

    public Integer getWeatherDataId() {
        return WeatherDataId;
    }

    public void setWeatherDataId(Integer weatherDataId) {
        WeatherDataId = weatherDataId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(int timeZone) {
        this.timeZone = timeZone;
    }

    public CoordinateEntity getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateEntity coordinate) {
        this.coordinate = coordinate;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public SystemDataEntity getSystemData() {
        return systemData;
    }

    public void setSystemData(SystemDataEntity systemData) {
        this.systemData = systemData;
    }

    public long getCalculationDateTime() {
        return calculationDateTime;
    }

    public void setCalculationDateTime(long calculationDateTime) {
        this.calculationDateTime = calculationDateTime;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public MainDataEntity getMainData() {
        return mainData;
    }

    public void setMainData(MainDataEntity mainData) {
        this.mainData = mainData;
    }

    public WindEntity getWind() {
        return wind;
    }

    public void setWind(WindEntity wind) {
        this.wind = wind;
    }

    public RainEntity getRain() {
        return rain;
    }

    public void setRain(RainEntity rain) {
        this.rain = rain;
    }

    public SnowEntity getSnow() {
        return snow;
    }

    public void setSnow(SnowEntity snow) {
        this.snow = snow;
    }

    public CloudEntity getClouds() {
        return clouds;
    }

    public void setClouds(CloudEntity clouds) {
        this.clouds = clouds;
    }

    public List<WeatherEntity> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherEntity> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "WeatherDataEntity{" +
                "WeatherDataId=" + WeatherDataId +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", cod=" + cod +
                ", timeZone=" + timeZone +
                ", coordinate=" + coordinate +
                ", base='" + base + '\'' +
                ", systemData=" + systemData +
                ", calculationDateTime=" + calculationDateTime +
                ", visibility=" + visibility +
                ", mainData=" + mainData +
                ", wind=" + wind +
                ", rain=" + rain +
                ", snow=" + snow +
                ", clouds=" + clouds +
                ", weather=" + weather +
                '}';
    }
}
