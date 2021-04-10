package com.oddle.app.weather.entity;

import com.oddle.app.weather.model.CoordModel;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "city")
@Data
@EqualsAndHashCode(callSuper = false)
public class City extends OddleEntity {

    @Transient
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private CoordModel coordModel;

    @Basic
    @Column(name = "name")
    private String name;

    public void setLongitude(double longitude) {
        coordModel.setLon(longitude);
    }

    @Basic
    @Column(name = "longitude")
    @Access(AccessType.PROPERTY)
    public double getLongitude() {
        return coordModel.getLon();
    }

    public void setLatitude(double latitude) {
        coordModel.setLat(latitude);
    }

    @Basic
    @Column(name = "latitude")
    @Access(AccessType.PROPERTY)
    public double getLatitude() {
        return coordModel.getLat();
    }

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Weather> weathers;

}
