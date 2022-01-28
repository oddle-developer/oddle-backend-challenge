package com.oddle.app.weather.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "weather")
public class Weather extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "weather_id")
    private Long weatherId;

    @NotEmpty(message = "Please input main weather")
    @Column(name = "main")
    private String main;

    @Lob
    @Column(name = "description")
    private String description;
}
