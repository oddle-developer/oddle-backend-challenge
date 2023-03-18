package com.oddle.app.weather.repository;

import com.oddle.app.weather.enumeration.WeatherCodeMainEnum;
import com.oddle.app.weather.model.entity.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class WeatherRepositoryTest {

    @Autowired
    WeatherRepository weatherRepository;

    @Test
    void checkIfRepositoryIsNotNull() {
        Weather result = weatherRepository.findByName("Ho Chi Minh City").get();
        assertThat(result.getCodes().get(0).getWeatherCodeMain()).isEqualTo(WeatherCodeMainEnum.CLEAR.toString());
    }

    @Test
    void checkFindBetween(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime from = LocalDateTime.parse("2023-03-18 12:56:27.000", formatter);
        LocalDateTime to = LocalDateTime.parse("2023-03-18 13:02:32.000", formatter);
        PageRequest pageRequest = PageRequest.of(1,2);
        Page<Weather> result = weatherRepository.findAllByCreatedDateBetween(from, to, pageRequest);
        assertThat(result.getContent().get(0).getCodes().get(0).getWeatherCodeMain()).isEqualTo(WeatherCodeMainEnum.CLEAR.toString());
    }
}
