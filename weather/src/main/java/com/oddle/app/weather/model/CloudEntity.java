package com.oddle.app.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "cloud")
public class CloudEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cloud_id")
    private Integer cloud_id;

    private short cloudiness;

    @JsonProperty("cloudiness")
    private short getCloudiness() {
        return cloudiness;
    }

    @JsonProperty("all")
    private void setCloudiness(short cloudiness) {
        this.cloudiness = cloudiness;
    }

    public Integer getCloud_id() {
        return cloud_id;
    }

    public void setCloud_id(Integer cloud_id) {
        this.cloud_id = cloud_id;
    }

    @Override
    public String toString() {
        return "CloudEntity{" +
                "cloud_id=" + cloud_id +
                ", cloudiness=" + cloudiness +
                '}';
    }
}
