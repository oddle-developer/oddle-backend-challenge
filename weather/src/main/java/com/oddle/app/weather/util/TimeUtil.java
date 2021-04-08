package com.oddle.app.weather.util;

import java.time.*;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

    private TimeUtil() {
    }

    public static LocalDate convertToUTCDate(LocalDate time, TimeZone timeZone) {
        ZonedDateTime todayAtZone = ZonedDateTime.of(time.atStartOfDay(), timeZone.toZoneId());
        return todayAtZone.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
    }

    public static Date getStartTimeOfDay(LocalDate localDate) {
        return Date.from(
                localDate
                        .atStartOfDay()
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    public static Date getEndTimeOfDay(LocalDate localDate) {
        return Date.from(
                localDate
                        .atTime(LocalTime.MAX)
                        .atZone(ZoneId.systemDefault()).toInstant()
        );
    }
}
