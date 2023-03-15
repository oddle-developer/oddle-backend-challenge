package com.oddle.app.weather.enumeration;

public enum WeatherCodeEnum {

    RAIN("Rain"),
    SNOW("Snow"),
    EXTREME("Extreme"),
    THUNDERSTORM("Thunderstorm"),
    DRIZZLE("Drizzle"),
    MIST("Mist"),
    SMOKE("Smoke"),
    HAZE("Haze"),
    DUST("Dust"),
    FOG("Fog"),
    SAND("Sand"),
    ASH("Ash"),
    SQUALL("Squall"),
    TORNADO("Tornado"),
    CLEAR("Clear"),
    CLOUD("Cloud");

    private String name;

    WeatherCodeEnum(String name) {
        this.name = name;
    }
}
