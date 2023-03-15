package com.oddle.app.weather.repository;

import com.oddle.app.weather.model.entity.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WeatherRepositoryTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void checkIfRepositoryIsNotNull() {
        Weather result = weatherRepository.findWeatherByNameCustom("Ho Chi Minh City").get();
        assertThat(result.getName()).isEqualTo("Ho Chi Minh City");
    }
}
