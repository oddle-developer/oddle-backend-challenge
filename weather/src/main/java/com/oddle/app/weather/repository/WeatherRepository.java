package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.internalmapper.IWeatherSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<IWeatherSummary, Long>, WeatherRepositoryExtension {
}
