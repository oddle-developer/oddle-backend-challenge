package com.oddle.app.weather.model.externalmapper;

import java.math.BigDecimal;

public class EWeatherMain {
    private BigDecimal temp;
    private BigDecimal feels_like;
    private BigDecimal temp_min;
    private BigDecimal temp_max;
    private BigDecimal pressure;
    private BigDecimal humidity;

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public BigDecimal getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(BigDecimal feels_like) {
        this.feels_like = feels_like;
    }

    public BigDecimal getPressure() {
        return pressure;
    }

    public void setPressure(BigDecimal pressure) {
        this.pressure = pressure;
    }

    public BigDecimal getHumidity() {
        return humidity;
    }

    public void setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(BigDecimal temp_min) {
        this.temp_min = temp_min;
    }

    public BigDecimal getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(BigDecimal temp_max) {
        this.temp_max = temp_max;
    }

    @Override
    public String toString() {
        return "Main [temp=" + temp + ", feels_like=" + feels_like + ", temp_min=" + temp_min + ", temp_max=" + temp_max
                + ", pressure=" + pressure + ", humidity=" + humidity + "]";
    }
}
