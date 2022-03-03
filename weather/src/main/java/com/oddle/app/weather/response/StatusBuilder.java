package com.oddle.app.weather.response;

import java.util.HashMap;

public class StatusBuilder {
    private static StatusBuilder statusBuilder = null;
    private HashMap<String, Response> statusMap = null;

    private StatusBuilder()
    {
        statusMap = new HashMap<String, Response>();

        String statusName = Status.SUCCESS.name();
        String rc = Status.SUCCESS.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "Your request successfully completed"));

        statusName = Status.NO_HISTORICAL_DATA.name();
        rc = Status.NO_HISTORICAL_DATA.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "There is no historical data for the specified city at the moment"));

        statusName = Status.CITY_NOT_FOUND.name();
        rc = Status.CITY_NOT_FOUND.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "Sorry we cannot find the city that you looking for"));

        statusName = Status.FAILED_TO_SAVE_DATA.name();
        rc = Status.FAILED_TO_SAVE_DATA.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "There are errors when saving your data, please try again later"));

        statusName = Status.FAILED_TO_UPDATE_DATA.name();
        rc = Status.FAILED_TO_UPDATE_DATA.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "There are errors when updating your data, please try again later"));

        statusName = Status.FAILED_TO_DELETE_DATA.name();
        rc = Status.FAILED_TO_DELETE_DATA.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "There are errors when deleting your data, please try again later"));


        statusName = Status.DATA_ALREADY_EXISTS.name();
        rc = Status.DATA_ALREADY_EXISTS.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "The data is already exists. Please input another data"));

        statusName = Status.DATA_NOT_FOUND.name();
        rc = Status.DATA_NOT_FOUND.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "We cannot find the specified data"));

        statusName = Status.UNDEFINED_ERROR.name();
        rc = Status.UNDEFINED_ERROR.responseCode();
        statusMap.put(statusName, new Response(
                rc, statusName, "We encountered a problem in our system"));
    }

    // static method to create instance of Singleton class
    private static StatusBuilder getInstance()
    {
        if (statusBuilder == null)
            statusBuilder = new StatusBuilder();

        return statusBuilder;
    }

    public static Response getStatus(String arg) {
        StatusBuilder builder = getInstance();

        if(arg == null) return builder.statusMap.get("UNDEFINED_ERROR");

        if(builder.statusMap.containsKey(arg)) {
            return builder.statusMap.get(arg);
        } else {
            return builder.statusMap.get("UNDEFINED_ERROR");
        }
    }
}
