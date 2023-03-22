package com.oddle.app.weather.enumeration;

public enum WeatherCodeMainEnum {

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

    WeatherCodeMainEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
