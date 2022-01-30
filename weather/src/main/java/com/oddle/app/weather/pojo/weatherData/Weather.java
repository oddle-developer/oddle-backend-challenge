package com.oddle.app.weather.pojo.weatherData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {
    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty("main")
    public String getGroup() {
        return main;
    }

    @JsonProperty("main")
    public void setGroup(String group) {
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
}
