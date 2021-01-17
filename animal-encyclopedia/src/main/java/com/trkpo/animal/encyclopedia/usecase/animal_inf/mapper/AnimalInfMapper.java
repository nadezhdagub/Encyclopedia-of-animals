package com.trkpo.animal.encyclopedia.usecase.animal_inf.mapper;

import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto;
import com.trkpo.animal.encyclopedia.config.AppMapperConfig;
import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = AppMapperConfig.class)
public interface AnimalInfMapper {
    AnimalInf createAnimalInfFromDto(AnimalInfDto animalInfDto);
    AnimalInfDto toDto(AnimalInf animalInf);
    void updateAnimalInfFromDto(AnimalInfDto animalInfDto, @MappingTarget AnimalInf animalInf);

}
