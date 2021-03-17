package com.oddle.app.weather.repositories;

import com.oddle.app.weather.models.repositories.WeatherRecord;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRecordRepository extends JpaRepository<WeatherRecord, Long> {
  public Optional<WeatherRecord> findById(Long weatherId);
  public List<WeatherRecord> findAllByCityName(String cityName, Sort sort);

  public void deleteById(Long historyId);
}
