package com.trkpo.animal.encyclopedia.usecase.animal_photo.mapper;

import com.trkpo.animal.encyclopedia.api.model.animal_photo.AnimalPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.animal_photo.AnimalPhotoDto.AnimalPhotoDtoBuilder;
import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T07:35:15+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9.1 (Ubuntu)"
)
@Component
public class AnimalPhotoMapperImpl implements AnimalPhotoMapper {

    @Override
    public AnimalPhotoDto toDto(AnimalPhoto areaPhoto) {
        if ( areaPhoto == null ) {
            return null;
        }

        AnimalPhotoDtoBuilder animalPhotoDto = AnimalPhotoDto.builder();

        animalPhotoDto.id( areaPhoto.getId() );
        animalPhotoDto.animal_photo( areaPhoto.getAnimal_photo() );

        return animalPhotoDto.build();
    }
}
