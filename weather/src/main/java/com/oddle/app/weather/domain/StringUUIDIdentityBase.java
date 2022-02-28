package com.oddle.app.weather.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class StringUUIDIdentityBase {
    private String id;

    @Id
    @GeneratedValue(generator = "hbm_uuid")
    @GenericGenerator(name = "hbm_uuid", strategy = "uuid")
    @Column(name = "ID_")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
