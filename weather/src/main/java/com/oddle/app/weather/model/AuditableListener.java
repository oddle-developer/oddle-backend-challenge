package com.oddle.app.weather.model;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.oddle.app.weather.util.DateUtil;
public class AuditableListener {
	
	
    @PrePersist
    public void prePersist(StandardAudibleEntity e) {
    	LocalDateTime now = DateUtil.getCurrentLocalDateTimeGmtPlus8();

        e.setDtimeCreated(now);
        e.setDtimeUpdated(now);
    }

    @PreUpdate
    public void preUpdate(StandardAudibleEntity e) {    	
    	LocalDateTime now = DateUtil.getCurrentLocalDateTimeGmtPlus8(); 	
        e.setDtimeUpdated(now);
    }
    
}
