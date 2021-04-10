package com.oddle.app.weather.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.oddle.app.weather.data.json.openweather.deserilizer.CoordDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CoordModel {

    private double lon;

    private double lat;

}
