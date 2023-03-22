package com.oddle.app.weather.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@UtilityClass
public class DateUtils {

    public LocalDateTime convertStringToLocalDateTime(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(time, formatter);
    }

    /*
        Convert from Long to Date need to multiply 1000
     */
    public Date convertLongToDate(Long time){
        return new Date(time * 1000L);
    }
}
