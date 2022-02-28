package com.oddle.app.weather.repository;

import com.oddle.app.weather.domain.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CityRepository extends PagingAndSortingRepository<City, Long> {
    City findByCityCode(String cityCode);
    @Query(value = "SELECT * FROM master_city mc where mc.city_name like %?1% ", nativeQuery = true)
    List<City> findByCityNameLike(String cityName);
    List<City> findAll();
    @Query(value = "SELECT * FROM master_city mc where mc.id_ = ?1 ", nativeQuery = true)
    City findOne(Long id);
}
