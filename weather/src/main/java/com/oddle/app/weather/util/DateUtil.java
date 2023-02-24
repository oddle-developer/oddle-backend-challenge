package com.oddle.app.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static String convertEpochToDate(Long epoch) {
        return formatter.format(new Date(epoch * 1000));
    }
}
