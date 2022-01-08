package com.oddle.app.weather.dto;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class WeatherResponse extends BaseResponse {
    private Long id;
    private String city;
    private LocalDateTime timestamp;
    private JsonNode jsonData;

	public WeatherResponse(Long id, String city, LocalDateTime timestamp, JsonNode jsonData) {
		super();
		this.id = id;
		this.city = city;
		this.timestamp = timestamp;
		this.jsonData = jsonData;
	}
}
