package com.trkpo.animal.encyclopedia.usecase.animal_inf.mapper;

import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto;
import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto.AnimalInfDtoBuilder;
import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import com.trkpo.animal.encyclopedia.entity.AnimalInf.AnimalInfBuilder;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-25T07:35:14+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.9.1 (Ubuntu)"
)
@Component
public class AnimalInfMapperImpl implements AnimalInfMapper {

    @Override
    public AnimalInf createAnimalInfFromDto(AnimalInfDto animalInfDto) {
        if ( animalInfDto == null ) {
            return null;
        }

        AnimalInfBuilder animalInf = AnimalInf.builder();

        animalInf.id( animalInfDto.getId() );
        animalInf.inf_of_type( animalInfDto.getInf_of_type() );
        animalInf.inf_of_area( animalInfDto.getInf_of_area() );
        animalInf.inf_of_number( animalInfDto.getInf_of_number() );
        animalInf.inf_of_downsizing( animalInfDto.getInf_of_downsizing() );
        animalInf.inf_of_security( animalInfDto.getInf_of_security() );

        return animalInf.build();
    }

    @Override
    public AnimalInfDto toDto(AnimalInf animalInf) {
        if ( animalInf == null ) {
            return null;
        }

        AnimalInfDtoBuilder animalInfDto = AnimalInfDto.builder();

        animalInfDto.id( animalInf.getId() );
        animalInfDto.inf_of_type( animalInf.getInf_of_type() );
        animalInfDto.inf_of_area( animalInf.getInf_of_area() );
        animalInfDto.inf_of_number( animalInf.getInf_of_number() );
        animalInfDto.inf_of_downsizing( animalInf.getInf_of_downsizing() );
        animalInfDto.inf_of_security( animalInf.getInf_of_security() );

        return animalInfDto.build();
    }

    @Override
    public void updateAnimalInfFromDto(AnimalInfDto animalInfDto, AnimalInf animalInf) {
        if ( animalInfDto == null ) {
            return;
        }

        if ( animalInfDto.getId() != null ) {
            animalInf.setId( animalInfDto.getId() );
        }
        if ( animalInfDto.getInf_of_type() != null ) {
            animalInf.setInf_of_type( animalInfDto.getInf_of_type() );
        }
        if ( animalInfDto.getInf_of_area() != null ) {
            animalInf.setInf_of_area( animalInfDto.getInf_of_area() );
        }
        if ( animalInfDto.getInf_of_number() != null ) {
            animalInf.setInf_of_number( animalInfDto.getInf_of_number() );
        }
        if ( animalInfDto.getInf_of_downsizing() != null ) {
            animalInf.setInf_of_downsizing( animalInfDto.getInf_of_downsizing() );
        }
        if ( animalInfDto.getInf_of_security() != null ) {
            animalInf.setInf_of_security( animalInfDto.getInf_of_security() );
        }
    }
}
