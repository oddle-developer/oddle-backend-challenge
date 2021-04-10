package com.oddle.app.weather.data.entity;

import com.oddle.app.weather.util.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
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
    public void onPersist() {
        Timestamp currentUTC = getCurrentUTCTimeStamp();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        createTime = currentUTC;
        updateTime = currentUTC;
    }

    @PreUpdate
    public void updateTime() {
        updateTime = getCurrentUTCTimeStamp();
    }

    private Timestamp getCurrentUTCTimeStamp() {
        return Timestamp.valueOf(TimeUtil.getCurrentUTCTime());
    }

}
