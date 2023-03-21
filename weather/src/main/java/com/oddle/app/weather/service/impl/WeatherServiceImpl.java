package com.oddle.app.weather.service.impl;

import com.oddle.app.weather.cache.CacheModel;
import com.oddle.app.weather.cache.WeatherCache;
import com.oddle.app.weather.constants.CommonConstants;
import com.oddle.app.weather.exception.CommonBusinessException;
import com.oddle.app.weather.handler.CommonFilterHandler;
import com.oddle.app.weather.model.dto.HistoryWeatherDTO;
import com.oddle.app.weather.model.dto.WeatherCloudDTO;
import com.oddle.app.weather.model.dto.WeatherCodeDTO;
import com.oddle.app.weather.model.dto.WeatherCoordinateDTO;
import com.oddle.app.weather.model.dto.WeatherMainDTO;
import com.oddle.app.weather.model.dto.WeatherRainDTO;
import com.oddle.app.weather.model.dto.WeatherSnowDTO;
import com.oddle.app.weather.model.dto.WeatherSunDTO;
import com.oddle.app.weather.model.dto.WeatherWindDTO;
import com.oddle.app.weather.model.entity.Weather;
import com.oddle.app.weather.model.entity.WeatherCloud;
import com.oddle.app.weather.model.entity.WeatherCode;
import com.oddle.app.weather.model.entity.WeatherCoordinates;
import com.oddle.app.weather.model.entity.WeatherMain;
import com.oddle.app.weather.model.entity.WeatherRain;
import com.oddle.app.weather.model.entity.WeatherSnow;
import com.oddle.app.weather.model.entity.WeatherSun;
import com.oddle.app.weather.model.entity.WeatherWind;
import com.oddle.app.weather.model.filter.HistoryWeatherFilter;
import com.oddle.app.weather.model.response.Response;
import com.oddle.app.weather.model.update.WeatherUpdate;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.OpenWeatherService;
import com.oddle.app.weather.service.WeatherService;
import com.oddle.app.weather.utils.CacheUtils;
import com.oddle.app.weather.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class WeatherServiceImpl extends CommonFilterHandler<HistoryWeatherFilter, Weather> implements WeatherService {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    OpenWeatherService openWeatherService;

    @Override
    public Response saveWeather(WeatherUpdate update) {
        Weather weather = modelMapper.map(update, Weather.class);
        weather.setDateTimeCalculation(DateUtils.convertLongToDate(update.getDateTimeCalculation()));
        weatherRepository.save(weather);
        return Response.success("Ok");
    }

    @Override
    public Response editWeather(WeatherUpdate update) {
        this.checkIfWeatherExists(update.getId());
        Weather weather = modelMapper.map(update, Weather.class);
        weatherRepository.save(weather);
        return Response.success("Ok");
    }

    @Override
    public Map<String, Object> getHistoryWeather(HistoryWeatherFilter filter) {
        if (Objects.isNull(filter)) {
            throw new CommonBusinessException("Invalid filter", HttpStatus.BAD_REQUEST.value());
        }
        String cacheKey = CacheUtils.getCacheKeyForHistoryWeather(filter);
        CacheModel<Map<String, Object>> cacheModel = WeatherCache.getData(cacheKey);
        if (Objects.nonNull(cacheModel)) {
            return cacheModel.getData();
        }
        Map<String, Object> result = super.processFilter(filter);
        WeatherCache.putData(cacheKey, result, 10000L);
        return result;
    }

    @Override
    public Response deleteWeatherHistory(Long weatherId) {
        this.checkIfWeatherExists(weatherId);
        weatherRepository.deleteById(weatherId);
        return Response.success("Ok");
    }

    @Override
    protected Page<Weather> getData(HistoryWeatherFilter filter) {
        LocalDateTime fromDate = DateUtils.convertStringToLocalDateTime(filter.getFrom());
        LocalDateTime toDate = DateUtils.convertStringToLocalDateTime(filter.getTo());
        if (fromDate.isEqual(toDate)) {
            toDate = toDate.plusDays(1).toLocalDate().atStartOfDay();
        }
        PageRequest pageRequest = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        return weatherRepository.findAllByCreatedDateBetween(fromDate, toDate, pageRequest);
    }

    @Override
    protected void markUpData(Page<Weather> data, Map<String, Object> result) {
        List<HistoryWeatherDTO> list = new ArrayList<>();
        data.getContent().forEach(item -> {
            HistoryWeatherDTO dto = new HistoryWeatherDTO();
            dto.setBase(item.getBase());
            dto.setCod(item.getCod());
            dto.setId(item.getId());
            dto.setName(item.getName());
            dto.setTimezone(item.getTimezone());
            dto.setVisibility(item.getVisibility());
            dto.setDt(item.getDateTimeCalculation().getTime());
            item.getCodes().forEach(code -> dto.getWeather().add(this.mappingWeatherCode(code)));
            // Cloud
            if (Objects.nonNull(item.getWeatherCloud())) {
                dto.setCloud(this.mappingWeatherCloud(item.getWeatherCloud()));
            }
            // Coordinates
            if (Objects.nonNull(item.getWeatherCloud())) {
                dto.setCoord(this.mappingWeatherCoordinate(item.getWeatherCoordinates()));
            }

            //Main
            if (Objects.nonNull(item.getWeatherMain())) {
                dto.setMain(this.mappingWeatherMain(item.getWeatherMain()));
            }
            // rain
            if (Objects.nonNull(item.getWeatherRain())) {
                dto.setRain(this.mappingWeatherRain(item.getWeatherRain()));
            }
            // snow
            if (Objects.nonNull(item.getWeatherSnow())) {
                dto.setSnow(this.mappingWeatherSnow(item.getWeatherSnow()));
            }
            // sun
            if (Objects.nonNull(item.getWeatherSun())) {
                dto.setSys(this.mappingWeatherSun(item.getWeatherSun()));
            }
            // wind
            if (Objects.nonNull(item.getWeatherWind())) {
                dto.setWind(this.mappingWeatherWind(item.getWeatherWind()));
            }
            list.add(dto);
        });
        result.put(CommonConstants.DATA, list);
    }

    private void checkIfWeatherExists(Long weatherId) {
        Optional<Weather> weather = weatherRepository.findById(weatherId);
        if (!weather.isPresent()) {
            throw new CommonBusinessException("Weather History not found", HttpStatus.NOT_FOUND.value());
        }
    }

    private WeatherCodeDTO mappingWeatherCode(WeatherCode code) {
        WeatherCodeDTO weatherCodeDTO = new WeatherCodeDTO();
        weatherCodeDTO.setId(code.getId());
        weatherCodeDTO.setMain(code.getWeatherCodeMain().getName());
        weatherCodeDTO.setDescription(code.getWeatherCodeDescription());
        weatherCodeDTO.setIcon(code.getWeatherCodeIcon());
        return weatherCodeDTO;
    }

    private WeatherCloudDTO mappingWeatherCloud(WeatherCloud cloud) {
        WeatherCloudDTO weatherCloudDTO = new WeatherCloudDTO();
        weatherCloudDTO.setAll(cloud.getAllPercentages());
        return weatherCloudDTO;
    }

    private WeatherCoordinateDTO mappingWeatherCoordinate(WeatherCoordinates coordinates) {
        WeatherCoordinateDTO weatherCoordinateDTO = new WeatherCoordinateDTO();
        weatherCoordinateDTO.setLat(coordinates.getLatitude());
        weatherCoordinateDTO.setLon(coordinates.getLongitude());
        return weatherCoordinateDTO;
    }

    private WeatherMainDTO mappingWeatherMain(WeatherMain main) {
        WeatherMainDTO weatherMainDTO = new WeatherMainDTO();
        weatherMainDTO.setTemp(main.getTemperature());
        weatherMainDTO.setFeelsLike(main.getFeelsLike());
        weatherMainDTO.setTemperatureMin(main.getTemperatureMin());
        weatherMainDTO.setTemperatureMax(main.getTemperatureMax());
        weatherMainDTO.setPressure(main.getPressure());
        weatherMainDTO.setHumidity(main.getHumidity());
        weatherMainDTO.setSeaLevel(main.getSeaLevel());
        weatherMainDTO.setGroundLevel(main.getGroundLevel());
        return weatherMainDTO;
    }

    private WeatherRainDTO mappingWeatherRain(WeatherRain rain) {
        WeatherRainDTO weatherRainDTO = new WeatherRainDTO();
        weatherRainDTO.setOneHour(rain.getRainOneHour());
        weatherRainDTO.setThreeHours(rain.getRainThreeHours());
        return weatherRainDTO;
    }

    private WeatherSnowDTO mappingWeatherSnow(WeatherSnow snow) {
        WeatherSnowDTO weatherSnowDTO = new WeatherSnowDTO();
        weatherSnowDTO.setOneHour(snow.getSnowOneHour());
        weatherSnowDTO.setThreeHours(snow.getSnowThreeHours());
        return weatherSnowDTO;
    }

    private WeatherSunDTO mappingWeatherSun(WeatherSun sun) {
        WeatherSunDTO weatherSunDTO = new WeatherSunDTO();
        weatherSunDTO.setId(sun.getId());
        weatherSunDTO.setType(sun.getType());
        weatherSunDTO.setCountry(sun.getCountryCode());
        weatherSunDTO.setSunSet(sun.getSunSet().getTime());
        weatherSunDTO.setSunRise(sun.getSunRise().getTime());
        return weatherSunDTO;
    }

    private WeatherWindDTO mappingWeatherWind(WeatherWind wind) {
        WeatherWindDTO weatherWindDTO = new WeatherWindDTO();
        weatherWindDTO.setDeg(wind.getDirection());
        weatherWindDTO.setGust(wind.getGust());
        weatherWindDTO.setSpeed(wind.getSpeed());
        return weatherWindDTO;
    }
}
