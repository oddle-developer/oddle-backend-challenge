package com.oddle.app.weather.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oddle.app.weather.model.weatherdata.Weather;

public interface WeatherDataRepository extends JpaRepository<Weather, Long> {
	Optional<Weather> findByIdAndIsActive(Long id,boolean isActive);
	Page<Weather> findAllByIsActiveAndDateTimeCalculatedBetween(boolean isActive,LocalDateTime from,LocalDateTime to,Pageable pageable);
}
