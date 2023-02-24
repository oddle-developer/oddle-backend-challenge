package com.oddle.app.weather.model.externalmapper;

import java.math.BigDecimal;

public class Coordinate {
    private BigDecimal lon;
    private BigDecimal lat;

    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return "Coordinate [lon=" + lon + ", lat=" + lat + "]";
    }
}
