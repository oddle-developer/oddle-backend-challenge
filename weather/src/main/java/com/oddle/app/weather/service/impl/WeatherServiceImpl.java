package com.oddle.app.weather.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oddle.app.weather.util.ExternalConfigUtil;
import com.oddle.app.weather.WeatherErrorMap;
import com.oddle.app.weather.exception.WeatherException;
import com.oddle.app.weather.model.Weather;
import com.oddle.app.weather.repository.WeatherRepository;
import com.oddle.app.weather.service.RestService;
import com.oddle.app.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    RestService restService;

    private JsonNode getRangedWeather(String city, String from, String to) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<Weather> queryResult = weatherRepository.getWeather(city, from, to);
            ArrayNode arrayNode = objectMapper.createArrayNode();
            for (Weather weather : queryResult) {
                arrayNode.add(weather.toJson());
            }
            return assembleSuccessResponse(arrayNode, "Success get data");
        } catch (WeatherException e) {
            return assembleErrorResponse(e);
        }
    }

    /**
     * Get weather for a city on a certain date.
     * Check it on the DB first then request to external API if no data is found in DB.
     */
    private JsonNode getExactDateWeather(String city, String date) {
        try {
            Weather weather = weatherRepository.getWeather(city, date);
            if (weather != null) {
                return assembleSuccessResponse(weather.toJson(), "Success get data");
            }

            boolean isSameDay;
            try {
                isSameDay = isSameDay(assembleDate(date), new Date());
            } catch (WeatherException e) {
                return assembleErrorResponse(e);
            }

            if (isSameDay) {
                Map<String, String> queryParam = new HashMap<>();
                queryParam.put("q", city);
                queryParam.put("appid", ExternalConfigUtil.get("weather.service.appid"));

                String uri = ExternalConfigUtil.get("weather.service.endpoint");

                String restResult = restService.sendMessage(uri, queryParam);

                weather = new Weather();
                weather.setCity(city);
                weather.setDate(new Date());
                weather.setInfo(restResult);

                weatherRepository.store(weather);

                return assembleSuccessResponse(weather.toJson(), "Success get and store data");
            }
            ObjectNode objectNode = (new ObjectMapper()).createObjectNode();
            return assembleSuccessResponse(objectNode, "No data found");
        } catch (WeatherException e) {
            return assembleErrorResponse(e);
        }
    }

    @Override
    public JsonNode getWeather(String city, String from, String to, String date) {
        /** only provide city name input, treat it as get weather for today */
        if (from == null && to == null && date == null) {
            return getExactDateWeather(city, parseDate(new Date()));
        }

        /** provide required arguments for both exact and ranged query, response error */
        if (from != null && to != null && date != null) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.BAD_REQUEST, "Please choose ranged or exact date query"));
        }

        /** date is not null, treat it as exact date query */
        if (date != null) {
            /** date must be in correct format */
            if (!isValidDateFormat(date)) {
                return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
            }
            return getExactDateWeather(city, date);
        } else {
            if (from == null || to == null) {
                return assembleErrorResponse(new WeatherException(WeatherErrorMap.BAD_REQUEST, "Please provide both date range"));
            }
            if (!isValidDateFormat(from)) {
                return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
            }
            if (!isValidDateFormat(to)) {
                return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
            }
            return getRangedWeather(city, from, to);
        }
    }

    public JsonNode delete(String city, String date) {
        if (city == null) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, "City must not be null or empty"));
        }
        if (date == null) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, "Date must not be null or empty"));
        }
        if (!isValidDateFormat(date)) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
        }
        try {
            Weather weather = new Weather();
            weather.setCity(city);
            weather.setDate(assembleDate(date));

            weatherRepository.delete(weather);
            return assembleSuccessResponse(null, "Success delete data");
        } catch (WeatherException e) {
            return assembleErrorResponse(e);
        }
    }

    public JsonNode update(String city, String dateParam, String info) {
        if (dateParam == null) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, "Date must not be null or empty"));
        }
        if (!isValidDateFormat(dateParam)) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
        }
        if (info == null) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, "Body must not be null or empty"));
        }
        if (!isValidAndNonArrayDataStructure(info)) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid body structure"));
        }

        try {
            Weather weather = new Weather();
            weather.setCity(city);
            weather.setDate(assembleDate(dateParam));
            weather.setInfo(info);

            weatherRepository.update(weather);
            return assembleSuccessResponse(null, "Success update data");
        } catch (WeatherException e) {
            return assembleErrorResponse(e);
        }
    }

    public JsonNode storeWeather(String city, String date, String body) {
        if (date == null || date.isEmpty()) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, ExternalConfigUtil.get("mandatory.field") + "date"));
        }
        if (!isValidDateFormat(date)) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format"));
        }
        if (body == null || body.isEmpty()) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.MISSING_FIELD, ExternalConfigUtil.get("mandatory.field") + "body"));
        }
        if (!isValidAndNonArrayDataStructure(body)) {
            return assembleErrorResponse(new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid body format"));
        }

        try {
            Weather weather = new Weather();
            weather.setCity(city);
            weather.setDate(new Date());
            weather.setInfo(body);

            weatherRepository.store(weather);

            return assembleSuccessResponse(null, "Success store data");
        } catch (WeatherException e) {
            return assembleErrorResponse(e);
        }

    }

    private Date assembleDate(String date) throws WeatherException {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return simpleDateFormat.parse(date);
        } catch (Exception e) {
            throw new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid date format");
        }

    }

    private String parseDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    private boolean isValidDateFormat(String date) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            simpleDateFormat.parse(date);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private boolean isValidAndNonArrayDataStructure(String weatherInfo) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            if (objectMapper.readTree(weatherInfo) instanceof ArrayNode) {
                return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }

    private JsonNode assembleErrorResponse(WeatherException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("code", e.getErrorMap().getCode());
        rootNode.put("description", e.getErrorMap().getDescription());
        rootNode.put("message", e.getMessage());
        return rootNode;
    }

    private JsonNode assembleSuccessResponse(JsonNode data, String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();
        rootNode.put("code", WeatherErrorMap.SUCCESS.getCode());
        rootNode.put("message", message);
        if (data != null && !data.isEmpty()) {
            rootNode.put("date", data);
        }
        return rootNode;
    }

    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    private boolean isSameDay(Calendar cal1, Calendar cal2) {
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
}
