package com.oddle.app.weather.model.weatherdata;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.oddle.app.weather.model.StandardAudibleEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="weather_snow")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_snow", nullable = false))
public class WeatherSnow extends StandardAudibleEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1443902853523087201L;
	
	@Column(columnDefinition="Decimal(10,2) default '0'")
	private Double oneHour;
	
	@Column(columnDefinition="Decimal(10,2) default '0'")
	private Double threeHours;
}
