package com.oddle.app.weather.model.externalmapper;

import java.math.BigDecimal;

public class Clouds {
    private BigDecimal all;

    public BigDecimal getAll() {
        return all;
    }

    public void setAll(BigDecimal all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds [all=" + all + "]";
    }
}
