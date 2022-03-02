package com.oddle.app.weather.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class LongIdentityBase {
    private Long id;

    public LongIdentityBase() {
        super();
    }

    public LongIdentityBase(Long id) {
        super();
        this.id = id;
    }

    @Id
    @GeneratedValue(generator = "hibincr")
    @GenericGenerator(name = "hibincr", strategy = "identity")
    @Column(name = "ID_")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
