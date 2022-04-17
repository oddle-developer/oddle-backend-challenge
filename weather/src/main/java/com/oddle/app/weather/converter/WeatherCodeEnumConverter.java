package com.oddle.app.weather.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.oddle.app.weather.constant.WeatherCodeEnum;

@Converter(autoApply = true)
public class WeatherCodeEnumConverter implements AttributeConverter<WeatherCodeEnum, String> {

    @Override
    public String convertToDatabaseColumn(WeatherCodeEnum code) {
        if (code == null) {
            return null;
        }
        return code.getName();
    }

    @Override
    public WeatherCodeEnum convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(WeatherCodeEnum.values())
          .filter(c -> c.getName().equals(code))
          .findFirst()
          .orElseThrow(IllegalArgumentException::new);
    }
}
