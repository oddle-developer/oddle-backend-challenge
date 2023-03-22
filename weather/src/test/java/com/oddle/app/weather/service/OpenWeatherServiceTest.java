package com.oddle.app.weather.service;


import com.oddle.app.weather.config.OpenWeatherApiConfig;
import com.oddle.app.weather.exception.CommonBusinessException;
import com.oddle.app.weather.model.dto.WeatherDTO;
import com.oddle.app.weather.service.impl.OpenWeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherServiceTest {

    @Mock
    OpenWeatherApiConfig config;

    @InjectMocks
    OpenWeatherServiceImpl openWeatherService;

    @BeforeEach
    public void initial() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        /*
            use when to suppose our api config return these value
         */
        when(config.getKey()).thenReturn("f70a9e656976d0be7200f6cfbc7d5a11");
        when(config.getCurrentWeatherUrl()).thenReturn("https://api.openweathermap.org/data/2.5/weather");
        /*
           Need to use java reflect to run init method since test cant initiate @PostConstruct
         */
        Method postConstruct = OpenWeatherServiceImpl.class.getMethod("initRestTemplate", null);
        postConstruct.setAccessible(true);
        postConstruct.invoke(openWeatherService);
    }

    @Test
    void checkIfCanCallExternalAPI() {
        WeatherDTO result = openWeatherService.getCurrentWeather("New York");
        assertThat(result.getName()).isEqualTo("New York");
    }

    @Test
    void checkIfNullCityParam() {
        Exception thrown = assertThrows(
                CommonBusinessException.class,
                () -> openWeatherService.getCurrentWeather("")
        );
        /*
            Check message when city is invalid city
         */
        assertThat(thrown.getMessage()).isEqualTo("City Not found");
    }
}
