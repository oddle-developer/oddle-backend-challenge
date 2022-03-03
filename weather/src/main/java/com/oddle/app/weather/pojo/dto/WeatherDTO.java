package com.oddle.app.weather.pojo.dto;

public class WeatherDTO {
    private int weatherId;

    private String main;

    private String description;

    private String icon;

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
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

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "weatherId=" + weatherId +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
