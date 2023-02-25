package com.oddle.app.weather.model.externalmapper;

import java.math.BigDecimal;

public class EWeatherWind {
    private BigDecimal speed;
    private BigDecimal deg;

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public BigDecimal getDeg() {
        return deg;
    }

    public void setDeg(BigDecimal deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind [speed=" + speed + ", deg=" + deg + "]";
    }
}
