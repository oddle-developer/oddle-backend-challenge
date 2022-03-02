package com.oddle.app.weather.dto;

import com.oddle.app.weather.model.Weather;

import javax.persistence.Id;
import java.util.Date;

public class WeatherDTO {

    String city;
    Date date;
    String info;

    public WeatherDTO(String city, Date date, String info) {
        this.city = city;
        this.date = date;
        this.info = info;
    }

    public Weather toModel() {
        Weather weather = new Weather();
        weather.setCity(this.city);
        weather.setDate(this.date);
        weather.setInfo(this.info);
        return weather;
    }
}
