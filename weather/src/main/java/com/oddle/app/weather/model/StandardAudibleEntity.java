package com.oddle.app.weather.model;

import javax.persistence.MappedSuperclass;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;

import lombok.Data;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EntityListeners(AuditableListener.class)
@Data
@EqualsAndHashCode(callSuper=false)
public class StandardAudibleEntity extends BaseEntity {
	
    @Column(name = "dtime_updated", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtimeUpdated;
    
    @Column(name = "dtime_created", columnDefinition = "TIMESTAMP")
    private LocalDateTime dtimeCreated;
    
}
