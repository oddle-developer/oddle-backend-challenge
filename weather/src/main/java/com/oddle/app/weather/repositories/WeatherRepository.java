package com.oddle.app.weather.repositories;

import com.oddle.app.weather.entity.Weather;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface WeatherRepository extends JpaRepository<Weather, String>,
        PagingAndSortingRepository<Weather, String> {

    /**
     * Get the Weather Record by City Name within start date and end date
     * order by the descending update time
     * This method is used with Pageable to limit the return record
     *
     * @param cityName The City name
     * @param startDate The start date
     * @param endDate The end date
     * @param pageable The Pageable
     * @return the Weather Record
     */
    @Query(value = "SELECT weather FROM Weather weather " +
            "WHERE weather.city.name = :cityName AND weather.updateTime " +
            "BETWEEN :startDate AND :endDate ORDER BY weather.updateTime DESC",
            countQuery = "SELECT count(weather) FROM Weather weather " +
                    "WHERE weather.city.name = :cityName"
    )
    List<Weather> findByCityInRangeDesc(@Param("cityName") String cityName,
                                        @Param("startDate") Date startDate,
                                        @Param("endDate") Date endDate,
                                        Pageable pageable);
}
