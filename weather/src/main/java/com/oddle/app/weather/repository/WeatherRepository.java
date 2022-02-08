package com.oddle.app.weather.repository;

import com.oddle.app.weather.entity.WeatherEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends PagingAndSortingRepository<WeatherEntity, Long> {
}
