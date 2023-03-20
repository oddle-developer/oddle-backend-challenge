package com.oddle.app.weather.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.deserializer.WeatherDeserial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonDeserialize(using = WeatherDeserial.class)
public class WeatherResponseDto implements Serializable {

	private static final long serialVersionUID = -2779357385065635523L;

	private Date date;
	private String weatherType;
	private String weatherDesc;
	private String temporary;
	private String temporaryMin;
	private String temporaryMax;
	private String city;
	private String cod;
	private String message;
}
