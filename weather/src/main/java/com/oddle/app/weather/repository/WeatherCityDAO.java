package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.WeatherCity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherCityDAO extends JpaRepository<WeatherCity, Long> {
}
