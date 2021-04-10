package com.oddle.app.weather.data.json.openweather.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CoordNode {

    private double lon;

    private double lat;

}
