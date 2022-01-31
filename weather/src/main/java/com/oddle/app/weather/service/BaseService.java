package com.oddle.app.weather.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseService {
    @Autowired
    protected ModelMapper modelMapper;

    protected <T> T convertObject(Object source, Class<T> target) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return modelMapper.map(source, target);
    }

    protected <S, T> List<T> convertObject(List<S> source, Class<T> target) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        return source.stream()
                .map(e -> modelMapper.map(e, target))
                .collect(Collectors.toList());
    }
}
