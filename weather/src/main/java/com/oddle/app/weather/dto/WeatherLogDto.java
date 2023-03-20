package com.oddle.app.weather.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.deserializer.WeatherDeserial;
import java.io.Serializable;
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
public class WeatherLogDto implements Serializable {

	private static final long serialVersionUID = -1620885456354959774L;

	private long id;
	private String date;
	private String weatherType;
	private String weatherDesc;
	private String temporary;
	private String temporaryMin;
	private String temporaryMax;
	private String city;
}
