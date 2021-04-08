package com.oddle.app.weather.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
@EqualsAndHashCode
public abstract class OddleEntity {
    @Id
    private String id;

    @Basic
    @Column(name = "create_time")
    private Timestamp createTime;

    @Basic
    @Column(name = "update_time")
    private Timestamp updateTime;

    @PrePersist
    public void generateId() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
}
