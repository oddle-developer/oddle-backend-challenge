package com.oddle.app.weather.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather_sun")
@AttributeOverride(name = "id", column = @Column(name = "weather_sun_id", nullable = false))
public class WeatherSun extends BaseEntity {

    @Column
    public Integer type;

    @Column
    @Size(min =2 ,max = 2, message ="country code's size in sun must be 2 characters")
    public String countryCode;

    @Column
    @Size(max = 200, message ="description length should be shorter or equals 200")
    public String message;

    @Column
    public Date sunRise;

    @Column
    public Date sunSet;

}
