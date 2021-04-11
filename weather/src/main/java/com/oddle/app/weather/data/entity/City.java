package com.oddle.app.weather.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
@Data
@EqualsAndHashCode(callSuper = false)
public class City extends OddleEntity {

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "longitude")
    private double longitude;

    @Basic
    @Column(name = "latitude")
    private double latitude;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Weather> weathers;

}
