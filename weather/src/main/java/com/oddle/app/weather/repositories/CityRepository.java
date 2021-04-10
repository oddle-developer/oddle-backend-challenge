package com.oddle.app.weather.repositories;

import com.oddle.app.weather.data.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, String> {

    Optional<City> findByNameIgnoreCase(String cityName);

}
