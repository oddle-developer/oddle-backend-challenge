package com.oddle.app.weather.repository;

import com.oddle.app.weather.entity.WeatherLog;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherLog, Long> {

	List<WeatherLog> getByCityAndDateBetween(String city, Date startDate, Date endDate);

	boolean existsByCityAndDate(String city, Date date);
}
