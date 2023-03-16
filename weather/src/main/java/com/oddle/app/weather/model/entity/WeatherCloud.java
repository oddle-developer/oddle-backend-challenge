package com.oddle.app.weather.model.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "weather_cloud")
@AttributeOverride(name = "id", column = @Column(name = "weather_cloud_id", nullable = false))
public class WeatherCloud extends BaseEntity {

    @Column
    public Double allPercentages;


}
