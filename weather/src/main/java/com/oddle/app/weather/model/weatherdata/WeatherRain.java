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
@Table(name="weather_rain")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_rain", nullable = false))
public class WeatherRain extends StandardAudibleEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5732590867765120727L;
	
	@Column(columnDefinition="Decimal(10,2) default '0'")
	private Double oneHour;
	@Column(columnDefinition="Decimal(10,2) default '0'")
	private Double threeHours;

}
