package com.oddle.app.weather.model.weatherdata;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather_sun")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_sun", nullable = false))
public class WeatherSun extends StandardAudibleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1898295213143111093L;
	
	@Column(length = 10, nullable=false)
	private String country;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime sunrise;
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime sunset;
	
}
