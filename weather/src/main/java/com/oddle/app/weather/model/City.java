package com.oddle.app.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class City extends OddleEntity {
    private String name;

    private long longitude;

    private long latitude;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Weather> weathers;
}
