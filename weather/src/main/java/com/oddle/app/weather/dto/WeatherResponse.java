package com.oddle.app.weather.dto;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.lang.NonNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class WeatherResponse extends BaseResponse {
    private Long id;
    private String city;
    private LocalDateTime timestamp;
    private String jsonData;

    WeatherResponse(){
        super();
    }

    @PrePersist
    @PreUpdate
    private void updateTimestamp() {
        timestamp = LocalDateTime.now();
    }
}
