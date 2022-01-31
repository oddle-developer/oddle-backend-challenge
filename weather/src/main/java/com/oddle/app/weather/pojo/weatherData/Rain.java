package com.oddle.app.weather.pojo.weatherData;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rain {
    private int volumeInLast1h;
    private int volumeInLast3h;

    @JsonProperty("volumeInLast1h")
    public int getVolumeInLast1h() {
        return volumeInLast1h;
    }

    @JsonProperty("1h")
    public void setVolumeInLast1h(int volumeInLast1h) {
        this.volumeInLast1h = volumeInLast1h;
    }

    @JsonProperty("volumeInLast3h")
    public int getVolumeInLast3h() {
        return volumeInLast3h;
    }

    @JsonProperty("3h")
    public void setVolumeInLast3h(int volumeInLast3h) {
        this.volumeInLast3h = volumeInLast3h;
    }
}
