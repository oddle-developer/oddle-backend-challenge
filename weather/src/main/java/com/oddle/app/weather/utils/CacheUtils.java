package com.oddle.app.weather.utils;

import com.oddle.app.weather.constants.CommonConstants;
import com.oddle.app.weather.model.filter.HistoryWeatherFilter;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class CacheUtils {

    public static String getCacheKeyForCurrentWeather(String cityName) {
        return CommonConstants.WEATHER_CACHE_PREFIX + CommonConstants.COLON + cityName;
    }

    public static String getCacheKeyForHistoryWeather(HistoryWeatherFilter filter) {
        return CommonConstants.WEATHER_CACHE_PREFIX +
                CommonConstants.COLON +
                (StringUtils.isEmpty(filter.getCityName()) ? StringUtils.EMPTY : filter.getCityName()) +
                CommonConstants.COLON +
                filter.getFrom() +
                CommonConstants.COLON +
                filter.getTo();
    }
}
