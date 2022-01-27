package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherDAO extends JpaRepository<Weather, Long> {
}
