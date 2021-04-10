package com.oddle.app.weather.util;

import java.sql.Timestamp;
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

    public static LocalDateTime convertToCurrentTimeZone(Timestamp utcTime, TimeZone timeZone) {
        return utcTime
                .toLocalDateTime()
                .atZone(ZoneOffset.UTC)
                .withZoneSameInstant(timeZone.toZoneId())
                .toLocalDateTime();
    }

    public static LocalDateTime getCurrentUTCTime() {
        return LocalDateTime.now(ZoneOffset.UTC);
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
