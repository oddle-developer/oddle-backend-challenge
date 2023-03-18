package com.oddle.app.weather.model.entity;

import com.oddle.app.weather.enumeration.WeatherCodeMainEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather_code")
@AttributeOverride(name = "id", column = @Column(name = "weather_code_id", nullable = false))
public class WeatherCode extends BaseEntity {


    @Column(name = "weather_code_main")
    @Enumerated(EnumType.STRING)
    private WeatherCodeMainEnum weatherCodeMain;

    @Column(name = "weather_description")
    private String weatherCodeDescription;

    @Column(name = "weather_icon")
    private String weatherCodeIcon;

}
