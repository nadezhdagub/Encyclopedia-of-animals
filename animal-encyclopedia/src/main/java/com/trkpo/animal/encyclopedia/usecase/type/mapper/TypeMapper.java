package com.trkpo.animal.encyclopedia.usecase.type.mapper;

import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.config.AppMapperConfig;
import com.trkpo.animal.encyclopedia.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = AppMapperConfig.class)
public interface TypeMapper {
    TypeDto toDto(Type type);
    void updateTypeFromDto(TypeDto typeDto, @MappingTarget Type type);
}
