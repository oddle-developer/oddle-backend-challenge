package com.oddle.app.weather.utils;

import com.oddle.app.weather.constants.ApplicationConstants;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
  public static String formatDate(Date date){
    if(date == null) return null;
    //SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_PATTERN_WITH_TZ);
    SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_PATTERN);
    return sdf.format(date);
  }

  public static Date transformDate(String date) {
    if(date == null) return null;

    SimpleDateFormat sdf = new SimpleDateFormat(ApplicationConstants.DATE_PATTERN);

    Date parsedDate = null;
    try {
      parsedDate = sdf.parse(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return parsedDate;
  }
}
