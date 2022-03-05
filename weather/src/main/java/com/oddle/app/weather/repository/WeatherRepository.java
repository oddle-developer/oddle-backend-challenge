package com.oddle.app.weather.repository;

import com.oddle.app.weather.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    void deleteWeatherByCity(String city);

    @Modifying
    @Transactional
    @Query("UPDATE Weather w SET w.weatherDescription = :description where w.city = :city")
    Weather updateWeatherByCity(@Param("city") String city,
                                @Param("description") String description);
}
