package com.oddle.app.weather.repository;

import com.oddle.app.weather.entity.WeatherLog;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherLog, Long> {

	WeatherLog getAllByCity(String city);

	Optional<WeatherLog> getByCityAndDate(String city, String date);
}
