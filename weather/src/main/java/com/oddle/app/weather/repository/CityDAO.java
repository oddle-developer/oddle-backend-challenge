package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends JpaRepository<City, Long> {

}
