package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "snow")
public class SnowEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "snow_id")
    private Integer snowId;

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

    public Integer getSnowId() {
        return snowId;
    }

    public void setSnowId(Integer snowId) {
        this.snowId = snowId;
    }

    @Override
    public String toString() {
        return "SnowEntity{" +
                "snowId=" + snowId +
                ", volumeInLast1h=" + volumeInLast1h +
                ", volumeInLast3h=" + volumeInLast3h +
                '}';
    }
}
