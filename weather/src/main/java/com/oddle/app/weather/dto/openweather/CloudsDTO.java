package com.oddle.app.weather.dto.openweather;

import java.io.Serializable;

public class CloudsDTO implements Serializable {
    private int all;

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }
}
