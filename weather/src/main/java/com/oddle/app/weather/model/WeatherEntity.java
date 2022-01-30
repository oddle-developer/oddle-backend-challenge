package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "weather_entity")
public class WeatherEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "weather_id")
    private int id;
    private String main;
    private String description;
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "weather_data_id", nullable = false)
    private WeatherDataEntity weatherData;

    public WeatherEntity() {
    }

    public WeatherEntity(int id, String group, String description, String icon, WeatherDataEntity weatherData) {
        this.id = id;
        this.main = group;
        this.description = description;
        this.icon = icon;
        this.weatherData = weatherData;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("main")
    public String getMain() {
        return main;
    }

    @JsonProperty("main")
    public void setMain(String group) {
        this.main = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "id=" + id +
                ", group='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
