package com.oddle.app.weather.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "weather_log")
public class Weather {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public int id;
    @Column(name = "city")
    public String city;
    @Column(name="country_code")
    public String countryCode;
    @Column(name = "temperature")
    public double temperature;
    @Column(name = "weather_description")
    public String weatherDescription;
    @Column(name = "humidity")
    public double humidity;
    @Column(name = "pressure")
    public double pressure;
    @Column(name = "date")
    public Instant date;

}
