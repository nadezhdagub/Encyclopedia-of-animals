package com.trkpo.animal.encyclopedia.usecase.area_photo.mapper;

import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;
import com.trkpo.animal.encyclopedia.config.AppMapperConfig;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import com.trkpo.animal.encyclopedia.entity.Type;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = AppMapperConfig.class)
public interface AreaPhotoMapper {
    AreaPhotoDto toDto(AreaPhoto areaPhoto);
}
