package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {

    Optional<Weather> findById(Long id);

    Optional<Weather> findByName(String name);

    Page<Weather> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to, Pageable pageRequest);
}
