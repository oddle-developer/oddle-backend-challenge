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
@Table(name="weather_coordinates")
@EqualsAndHashCode(callSuper=true)
@Data
@AttributeOverride(name = "id", column = @Column(name = "id_weather_coordinates", nullable = false))
public class WeatherCoordinates extends StandardAudibleEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3689974685038402697L;
	
	@Column(columnDefinition="Decimal(10,4) default '0'")
	protected Double longitude;

	@Column(columnDefinition="Decimal(10,4) default '0'")
	protected Double latitude;

}
