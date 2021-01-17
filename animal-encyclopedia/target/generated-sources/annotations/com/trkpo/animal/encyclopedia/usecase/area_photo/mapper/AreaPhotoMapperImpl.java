package com.trkpo.animal.encyclopedia.usecase.area_photo.mapper;

import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto.AreaPhotoDtoBuilder;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T07:35:14+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9.1 (Ubuntu)"
)
@Component
public class AreaPhotoMapperImpl implements AreaPhotoMapper {

    @Override
    public AreaPhotoDto toDto(AreaPhoto areaPhoto) {
        if ( areaPhoto == null ) {
            return null;
        }

        AreaPhotoDtoBuilder areaPhotoDto = AreaPhotoDto.builder();

        areaPhotoDto.id( areaPhoto.getId() );
        areaPhotoDto.area_photo( areaPhoto.getArea_photo() );

        return areaPhotoDto.build();
    }
}
