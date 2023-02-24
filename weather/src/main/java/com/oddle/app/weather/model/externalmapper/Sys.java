package com.oddle.app.weather.model.externalmapper;

public class Sys {
    private int type;
    private int id;
    private String country;
    private Long sunrise;
    private Long sunset;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "Sys [type=" + type + ", id=" + id + ", country=" + country + ", sunrise=" + sunrise + ", sunset="
                + sunset + "]";
    }
}
