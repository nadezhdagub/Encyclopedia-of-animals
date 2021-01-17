package com.trkpo.animal.encyclopedia.usecase.animal_photo.mapper;

import com.trkpo.animal.encyclopedia.api.model.animal_photo.AnimalPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.config.AppMapperConfig;
import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = AppMapperConfig.class)
public interface AnimalPhotoMapper {
    AnimalPhotoDto toDto(AnimalPhoto areaPhoto);
}
