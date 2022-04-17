package com.oddle.app.weather.model.weatherdata;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather_main")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_main", nullable = false))
public class WeatherMain extends StandardAudibleEntity implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1291066338390707386L;
	

	private Double temperature;
	
	private Double feelsLike;
	
	private BigDecimal pressure;
	
	private Double humidity;
	
	private Double minimumTemperature;
	
	private Double maximumTemperature;
	
	private BigDecimal sealLevel;
	
	private BigDecimal groundLevel;
}
