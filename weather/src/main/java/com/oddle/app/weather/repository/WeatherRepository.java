package com.oddle.app.weather.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.oddle.app.weather.entity.Weathers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weathers, Long> {
    List<Weathers> findByTimestampBetween(LocalDateTime fromDt, LocalDateTime toDt);
}
