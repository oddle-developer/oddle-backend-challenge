package com.oddle.app.weather.mapper;

import java.util.List;

public interface EntityDBToDTOMapper<Entity, DTO> {
    DTO convertToDto(Entity entity);

    List<DTO> convertToDto(List<Entity> entityList);

    Entity convertToEntity(DTO dto);

    List<Entity> convertToEntity(List<DTO> dtoList);
}
