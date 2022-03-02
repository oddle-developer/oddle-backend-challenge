package com.oddle.app.weather.repository;

import com.oddle.app.weather.domain.WeatherHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface WeatherHistoryRepository extends PagingAndSortingRepository<WeatherHistory, String> {
    List<WeatherHistory> findAll();
    WeatherHistory findByCityCode(String cityCode);
    List<WeatherHistory> findAllByDeleted(boolean isDeleted);

    @Query(value = "SELECT * FROM weather_history wh where wh.is_deleted = ?1 " +
            "AND wh.date_modified >= ?2 ORDER BY wh.date_modified DESC",
            nativeQuery = true)
    List<WeatherHistory> findAllByDeletedAndDateModifiedAfter(boolean isDeleted, Date dtmStart);

    @Query(value = "SELECT * FROM weather_history wh where wh.is_deleted = ?1 " +
            "AND wh.date_modified between ?2 AND ?3 ORDER BY wh.date_modified DESC",
            nativeQuery = true)
    List<WeatherHistory> findAllByDeletedAndDateModifiedBetween(boolean isDeleted, Date dtmStart, Date dtmEnd);


    @Query(value = "SELECT * FROM weather_history wh where wh.city_name like %?1% ", nativeQuery = true)
    List<WeatherHistory> findByCityNameLike(String cityName);

    @Query(value = "SELECT * FROM weather_history wh where wh.id_ = ?1 ", nativeQuery = true)
    WeatherHistory findOne(String id);
}
