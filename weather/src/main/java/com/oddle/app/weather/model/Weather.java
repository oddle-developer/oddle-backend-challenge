package com.oddle.app.weather.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.oddle.app.weather.WeatherErrorMap;
import com.oddle.app.weather.dto.WeatherDTO;
import com.oddle.app.weather.exception.WeatherException;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SqlResultSetMapping(name="WeatherDTOMapping",
    classes = {
    @ConstructorResult(targetClass = WeatherDTO.class,
    columns = {@ColumnResult(name="city"),
    @ColumnResult(name="date"),
    @ColumnResult(name="info")}
    )}
)
@Entity
public class Weather implements Serializable {

    private static final long serialVersionUID = 3924745620025461333L;

    @Id
    private String city;
    @Id
    private Date date;
    private String info;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public JsonNode toJson() throws WeatherException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode rootNode = objectMapper.createObjectNode();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        JsonNode infoNode = null;
        try {
            infoNode = objectMapper.readTree(this.info);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WeatherException(WeatherErrorMap.ILLEGAL_FORMAT, "Invalid data structure on information.");
        }

        rootNode.put("city", this.city);
        rootNode.put("date", simpleDateFormat.format(this.date));
        rootNode.put("info", infoNode);

        return rootNode;
    }
}
