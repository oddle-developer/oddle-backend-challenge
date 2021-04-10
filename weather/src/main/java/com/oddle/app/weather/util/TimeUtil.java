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

    public static LocalDateTime convertToUTCTime(LocalDateTime localTime, TimeZone timeZone) {
        ZonedDateTime zoneTime = localTime.atZone(timeZone.toZoneId());
        return zoneTime.withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
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

    public static Timestamp getStartTimeOfDay(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atStartOfDay());
    }

    public static Timestamp getEndTimeOfDay(LocalDate localDate) {
        return Timestamp.valueOf(localDate.atTime(LocalTime.MAX));
    }
}
