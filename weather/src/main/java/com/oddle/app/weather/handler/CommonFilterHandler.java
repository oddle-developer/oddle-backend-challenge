package com.oddle.app.weather.handler;

import com.oddle.app.weather.constants.CommonConstants;
import com.oddle.app.weather.exception.CommonBusinessException;
import com.oddle.app.weather.model.dto.PageResponse;
import com.oddle.app.weather.model.entity.BaseEntity;
import com.oddle.app.weather.model.filter.BaseFilter;
import com.oddle.app.weather.utils.DateUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonFilterHandler<K extends BaseFilter, V extends BaseEntity> {

    public Map<String, Object> processFilter(K filter) {
        Map<String, Object> result = new HashMap<>();
        this.validation(filter);
        Page<V> data = this.getData(filter);
        this.markUpData(data, result);
        this.calculatePaging(data, result);
        return result;
    }

    protected abstract Page<V> getData(K filter);

    protected void markUpData(Page<V> data, Map<String, Object> result) {
        result.put(CommonConstants.DATA, data.getContent());
    }

    protected void calculatePaging(Page<V> data, Map<String, Object> result) {
        PageResponse response = PageResponse.builder()
                .pageNumber(data.getNumber())
                .pageSize(data.getSize())
                .totalPages(data.getTotalPages())
                .totalElements(data.getTotalElements())
                .build();
        result.put(CommonConstants.PAGE, response);
    }

    protected void validation(K filter) {
        try {
            LocalDateTime fromDate = DateUtils.convertStringToLocalDateTime(filter.getFrom());
            LocalDateTime toDate = DateUtils.convertStringToLocalDateTime(filter.getTo());
            if (fromDate.isAfter(toDate)) {
                throw new CommonBusinessException("Invalid value of From/To Date, From Date need to be the same/before To Date", HttpStatus.BAD_REQUEST.value());
            }
        } catch (DateTimeParseException e) {
            throw new CommonBusinessException("Invalid value from/to", HttpStatus.BAD_REQUEST.value());
        }
    }
}
