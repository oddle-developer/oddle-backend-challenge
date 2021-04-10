package com.oddle.app.weather.repositories;

import com.oddle.app.weather.entity.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<City, String> {

    Optional<City> findByName(String cityName);

}
