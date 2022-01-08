package com.oddle.app.weather.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="weather")
@Data
public class Weathers {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "city", length = 200, nullable = false, unique = false)
	private String city;
    @Column(name = "timestamp", length = 200, nullable = false, unique = false)
	private LocalDateTime timestamp;
    @Column(name = "jsonData", length = 200, nullable = false, unique = false)
    private String jsonData;
}
