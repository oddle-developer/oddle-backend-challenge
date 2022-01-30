package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "wind")
public class WindEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wind_id")
    private Integer windId;

    private float speed;
    private float degree;
    private float gust;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @JsonProperty("degree")
    public float getDegree() {
        return degree;
    }

    @JsonProperty("deg")
    public void setDegree(float degree) {
        this.degree = degree;
    }

    public float getGust() {
        return gust;
    }

    public void setGust(float gust) {
        this.gust = gust;
    }

    public Integer getWindId() {
        return windId;
    }

    public void setWindId(Integer windId) {
        this.windId = windId;
    }

    @Override
    public String toString() {
        return "WindEntity{" +
                "windId=" + windId +
                ", speed=" + speed +
                ", degree=" + degree +
                ", gust=" + gust +
                '}';
    }
}
