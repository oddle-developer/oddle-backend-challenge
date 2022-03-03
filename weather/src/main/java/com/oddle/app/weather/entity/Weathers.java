package com.oddle.app.weather.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="weathers")
@Data
public class Weathers {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "city", length = 200, nullable = false, unique = false)
	private String city;
    @Column(name = "timestamp", nullable = false, unique = false)
	private LocalDateTime timestamp;
    @Column(name = "json_data", columnDefinition="TEXT", nullable = false, unique = false)
    private String jsonData;
    
    @PrePersist
    @PreUpdate
    private void updateTimestamp() {
        timestamp = LocalDateTime.now();
    }

}
