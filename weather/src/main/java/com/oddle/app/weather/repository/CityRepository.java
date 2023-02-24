package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.internalmapper.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, String> {
}
