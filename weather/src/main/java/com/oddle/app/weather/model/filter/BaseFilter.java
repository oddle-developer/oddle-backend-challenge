package com.oddle.app.weather.model.filter;

import lombok.Data;

@Data
public class BaseFilter {

    private int pageNumber;

    private int pageSize;

    private String from;

    private String to;
}
