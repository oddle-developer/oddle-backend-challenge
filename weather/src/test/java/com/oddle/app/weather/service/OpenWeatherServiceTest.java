package com.oddle.app.weather.service;

import com.oddle.app.weather.service.impl.OpenWeatherServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {


    @InjectMocks
    OpenWeatherServiceImpl openWeatherServiceImpl;


    @Test
    void checkIfCanCallExternalAPI() {
//        OpenWeatherDTO result = openWeatherServiceImpl.getCurrentWeather(params);
//        assertThat(result.getName()).isEqualTo("Zocca");
    }
}
