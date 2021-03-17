package com.oddle.app.weather.mapper;

import com.oddle.app.weather.models.repositories.Weather;
import com.oddle.app.weather.models.repositories.WeatherRecord;
import com.oddle.app.weather.models.rest.WeatherData;
import com.oddle.app.weather.models.rest.weather.Clouds;
import com.oddle.app.weather.models.rest.weather.Coordinate;
import com.oddle.app.weather.models.rest.weather.Main;
import com.oddle.app.weather.models.rest.weather.System;
import com.oddle.app.weather.models.rest.weather.Wind;
import com.oddle.app.weather.utils.CommonUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class WeatherDataMapper {
  public WeatherRecord createWeatherRecord(WeatherData data){

    return WeatherRecord.builder().cityId(data.getId())
      .cityName(data.getName())
      .timezone(data.getTimezone())
      .dt(data.getDt())
      .visibility(data.getVisibility())
      .base(data.getBase())
      .coordinateLongitude(data.getCoordinate().getLongitude())
      .coordinateLatitude(data.getCoordinate().getLatitude())
      //.weathers(getWeathers(data.getWeather()))
      .mainTemperature(data.getMain().getTemperature())
      .mainFeelsLike(data.getMain().getFeelsLike())
      .mainTempMin(data.getMain().getTempMin())
      .mainTempMax(data.getMain().getTempMax())
      .mainPressure(data.getMain().getPressure())
      .mainHumidity(data.getMain().getHumidity())
      .windSpeed(data.getWind().getSpeed())
      .windDegree(data.getWind().getDegree())
      .cloudsAll(data.getClouds().getAll())
      .systemType(data.getSystem().getType())
      .systemId(data.getSystem().getId())
      .systemCountry(data.getSystem().getCountry())
      .systemSunrise(data.getSystem().getSunrise())
      .systemSunset(data.getSystem().getSunset()).build();
  }

  public List<Weather> createWeathers(List<com.oddle.app.weather.models.rest.weather.Weather> weathers) {

    return weathers.stream().map(weather -> Weather.builder().weatherId(weather.getId())
      .main(weather.getMain())
      .description(weather.getDescription())
      .icon(weather.getIcon()).build()).collect(Collectors.toList());
  }
  public List<WeatherData> getWeatherDatas(List<WeatherRecord> weatherHistories) {

    return weatherHistories.stream().map(history -> WeatherData.builder().coordinate(getCoordinate(history))
      .weather(getWeathers(history.getWeathers()))
      .base(history.getBase())
      .main(getMain(history))
      .visibility(history.getVisibility())
      .wind(getWind(history))
      .clouds(getClouds(history.getCloudsAll()))
      .dt(history.getDt())
      .system(getSystem(history))
      .timezone(history.getTimezone())
      .id(history.getCityId())
      .name(history.getCityName())
      .datePosted(CommonUtil.formatDate(history.getDatePosted()))
      .historyId(history.getId()).build()).collect(Collectors.toList());
  }

  private Coordinate getCoordinate(WeatherRecord history) {

    return Coordinate.builder().longitude(history.getCoordinateLongitude())
      .latitude(history.getCoordinateLatitude()).build();
  }

  private List<com.oddle.app.weather.models.rest.weather.Weather> getWeathers(List<Weather> weathers) {

    return weathers.stream().map(weather -> com.oddle.app.weather.models.rest.weather.Weather.builder().id(weather.getWeatherId())
      .main(weather.getMain())
      .description(weather.getDescription())
      .icon(weather.getIcon()).build()).collect(Collectors.toList());
  }

  private Main getMain(WeatherRecord history) {

    return Main.builder().temperature(history.getMainTemperature())
      .feelsLike(history.getMainFeelsLike())
      .tempMin(history.getMainTempMin())
      .tempMax(history.getMainTempMax())
      .pressure(history.getMainPressure())
      .humidity(history.getMainHumidity()).build();
  }

  private Wind getWind(WeatherRecord history) {

    return Wind.builder().speed(history.getWindSpeed())
      .degree(history.getWindDegree()).build();
  }

  private Clouds getClouds(Integer cloudsAll) {

    return Clouds.builder().all(cloudsAll).build();
  }

  private System getSystem(WeatherRecord history) {

    return System.builder().type(history.getSystemType())
      .id(history.getSystemId())
      .country(history.getSystemCountry())
      .sunrise(history.getSystemSunrise())
      .sunset(history.getSystemSunset()).build();
  }

  public WeatherRecord updateWeatherRecord(WeatherRecord history, WeatherData data) {

    if(!ObjectUtils.isEmpty(data.getId())) history.setCityId(data.getId());
    if(!ObjectUtils.isEmpty(data.getName())) history.setCityName(data.getName());
    if(!ObjectUtils.isEmpty(data.getTimezone())) history.setTimezone(data.getTimezone());
    if(!ObjectUtils.isEmpty(data.getDt())) history.setDt(data.getDt());
    if(!ObjectUtils.isEmpty(data.getVisibility())) history.setVisibility(data.getVisibility());
    if(!ObjectUtils.isEmpty(data.getBase())) history.setBase(data.getBase());
    if(!ObjectUtils.isEmpty(data.getCoordinate())) {
      if (!ObjectUtils.isEmpty(data.getCoordinate().getLongitude())) history.setCoordinateLongitude(data.getCoordinate().getLongitude());
      if (!ObjectUtils.isEmpty(data.getCoordinate().getLatitude())) history.setCoordinateLatitude(data.getCoordinate().getLatitude());
    }
    if(!ObjectUtils.isEmpty(data.getWeather())) history.setWeathers(updateWeathers(history.getWeathers(),data.getWeather()));
    if(!ObjectUtils.isEmpty(data.getMain())) {
      if (!ObjectUtils.isEmpty(data.getMain().getTemperature())) history.setMainTemperature(data.getMain().getTemperature());
      if (!ObjectUtils.isEmpty(data.getMain().getFeelsLike())) history.setMainFeelsLike(data.getMain().getFeelsLike());
      if (!ObjectUtils.isEmpty(data.getMain().getTempMin())) history.setMainTempMin(data.getMain().getTempMin());
      if (!ObjectUtils.isEmpty(data.getMain().getTempMax())) history.setMainTempMax(data.getMain().getTempMax());
      if (!ObjectUtils.isEmpty(data.getMain().getPressure())) history.setMainPressure(data.getMain().getPressure());
      if (!ObjectUtils.isEmpty(data.getMain().getHumidity())) history.setMainHumidity(data.getMain().getHumidity());
    }
    if(!ObjectUtils.isEmpty(data.getWind())) {
      if (!ObjectUtils.isEmpty(data.getWind().getSpeed())) history.setWindSpeed(data.getWind().getSpeed());
      if (!ObjectUtils.isEmpty(data.getWind().getDegree())) history.setWindDegree(data.getWind().getDegree());
    }
    if(!ObjectUtils.isEmpty(data.getClouds())) {
      if (!ObjectUtils.isEmpty(data.getClouds().getAll())) history.setCloudsAll(data.getClouds().getAll());
    }
    if(!ObjectUtils.isEmpty(data.getSystem())) {
      if (!ObjectUtils.isEmpty(data.getSystem().getType())) history.setSystemType(data.getSystem().getType());
      if (!ObjectUtils.isEmpty(data.getSystem().getId())) history.setSystemId(data.getSystem().getId());
      if (!ObjectUtils.isEmpty(data.getSystem().getCountry())) history.setSystemCountry(data.getSystem().getCountry());
      if (!ObjectUtils.isEmpty(data.getSystem().getSunrise())) history.setSystemSunrise(data.getSystem().getSunrise());
      if (!ObjectUtils.isEmpty(data.getSystem().getSunset())) history.setSystemSunset(data.getSystem().getSunset());
    }

    return history;
  }
  private List<Weather>  updateWeathers(List<Weather> weathers, List<com.oddle.app.weather.models.rest.weather.Weather> dataWeather) {



    return weathers.stream().map(entry -> {
      Optional<com.oddle.app.weather.models.rest.weather.Weather> weather = dataWeather.stream().filter(data -> entry.getWeatherId().equals(data.getId())).findFirst();

      if(weather.isPresent()) {
        entry.setDescription(weather.get().getDescription());
        entry.setIcon(weather.get().getIcon());
        entry.setMain(weather.get().getMain());
      }

      return entry;
    }).collect(Collectors.toList());
  }
}
