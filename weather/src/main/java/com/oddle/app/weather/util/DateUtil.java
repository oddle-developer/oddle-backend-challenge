package com.oddle.app.weather.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import com.oddle.app.weather.exception.DateException;

public class DateUtil {
	
	private DateUtil() {	
		
	}
	
	public static final String RELEASE_DATE = "1971/01/01";
	private static final long MIN_TIMESTAMP;

	static {
	    try {
	        MIN_TIMESTAMP = new SimpleDateFormat("yyyy/MM/dd").parse(RELEASE_DATE).getTime()/1000;
	    } catch (ParseException e) {
	        throw new AssertionError(e);
	    }
	}
	
	public static LocalDateTime getCurrentLocalDateTimeGmtPlus8() {
    	LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Hong_Kong"));
    	return now;
	}
	
	public static LocalDateTime getLocalDateTimeUTC(long epochtime) throws DateException {
		if(!validTimestamp(epochtime))
			throw new DateException("not a valid epoch time for {"+epochtime+"}");
		
		LocalDateTime localDateTime = Instant.ofEpochMilli(epochtime*1000).atZone(ZoneOffset.UTC).toLocalDateTime();
		return localDateTime;
	}


	public static final boolean validTimestamp(long ts) {
	    return ts >= MIN_TIMESTAMP && ts <= (System.currentTimeMillis()/1000);
	}
	
}
