package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.exception.CityNotFoundException;
import com.oddle.app.weather.exception.WeatherDataNotFoundException;
import com.oddle.app.weather.exception.WeatherNotFoundException;
import com.oddle.app.weather.model.City;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.model.WeatherData;
import com.oddle.app.weather.pojo.dto.WeatherDTO;
import com.oddle.app.weather.pojo.dto.WeatherDataDTO;
import com.oddle.app.weather.repository.CityRepository;
import com.oddle.app.weather.repository.WeatherDataRepository;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.BaseService;
import com.oddle.app.weather.service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WeatherDataServiceImpl extends BaseService implements WeatherDataService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<WeatherDataDTO> getWeatherFromPastDate(String fromDate, String toDate) {
        return null;
    }

    @Override
    public WeatherDataDTO getCityWeatherById(int weatherDataId) {
        WeatherData weatherData = weatherDataRepository.findById(weatherDataId)
                .orElseThrow(() ->
                        new WeatherDataNotFoundException("id " + weatherDataId + " not found"));
        return convertObject(weatherData, WeatherDataDTO.class);
    }

    @Override
    public WeatherDataDTO saveWeatherData(WeatherDataDTO weatherDataDTO) {
        City city = cityRepository.findById(weatherDataDTO.getCity().getCityId())
                .orElseThrow(() ->
                        new CityNotFoundException("id " + weatherDataDTO.getCity().getCityId() + " not found"));
        List<Weather> weathers = new ArrayList<>();
        for (WeatherDTO w : weatherDataDTO.getWeathers()) {
            Weather weather = weatherRepository.findById(w.getWeatherId())
                    .orElseThrow(() ->
                            new WeatherNotFoundException("id " + w.getWeatherId() + " not found"));
            weathers.add(weather);
        }
        WeatherData weatherData = convertObject(weatherDataDTO, WeatherData.class);
        weatherData.setCity(city);
        weatherData.setWeathers(weathers);
        weatherData = weatherDataRepository.save(weatherData);
        return convertObject(weatherData, WeatherDataDTO.class);
    }

    @Override
    public WeatherDataDTO updateWeatherData(int weatherDataId, WeatherDataDTO weatherDataDTO) {
        WeatherData weatherData = weatherDataRepository.findById(weatherDataId)
                .orElseThrow(() ->
                        new WeatherDataNotFoundException("id " + weatherDataId + " not found"));
        City city = cityRepository.findById(weatherData.getCity().getCityId())
                .orElseThrow(() ->
                        new CityNotFoundException("id " + weatherData.getCity().getCityId() + " not found"));
        List<Weather> weathers = new ArrayList<>();
        for (WeatherDTO w : weatherDataDTO.getWeathers()) {
            Weather weather = weatherRepository.findById(w.getWeatherId())
                    .orElseThrow(() ->
                            new WeatherNotFoundException("id " + w.getWeatherId() + " not found"));
            weathers.add(weather);
        }
        weatherData.setCity(city);
        weatherData.setWeathers(weathers);
        weatherData.setBase(weatherDataDTO.getBase());
        weatherData.setHumidity(weatherDataDTO.getHumidity());
        weatherData.setPressure(weatherDataDTO.getPressure());
        weatherData.setFeelsLike(weatherDataDTO.getFeelsLike());
        weatherData.setTempMax(weatherDataDTO.getTempMax());
        weatherData.setTempMin(weatherDataDTO.getTempMin());
        weatherData.setVisibility(weatherDataDTO.getVisibility());
        return convertObject(weatherDataDTO, WeatherDataDTO.class);
    }

    @Override
    public void deleteWeatherData(int weatherDataId) {
        WeatherData weatherData = weatherDataRepository.findById(weatherDataId)
                .orElseThrow(() -> new WeatherDataNotFoundException("id " + weatherDataId + " not found"));
        weatherDataRepository.delete(weatherData);
    }
}
