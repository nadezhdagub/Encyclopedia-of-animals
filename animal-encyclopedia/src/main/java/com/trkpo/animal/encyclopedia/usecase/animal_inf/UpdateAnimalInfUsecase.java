package com.trkpo.animal.encyclopedia.usecase.animal_inf;

import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto;
import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.mapper.AnimalInfMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateAnimalInfUsecase {
    private final AnimalInfRepository animalInfRepository;
    private final AnimalInfMapper animalInfMapper;

    public AnimalInf execute(UUID animalInfId, AnimalInfDto animalinfDto) {
        return animalInfRepository.findById(animalInfId)
                .map(dbCompany -> {
                    animalInfMapper.updateAnimalInfFromDto(animalinfDto, dbCompany);
                    return animalInfRepository.save(dbCompany);
                })
                .orElseThrow(() -> new NotFoundException(
                        String.format("animal(%s) information not found", animalInfMapper.toString())));
    }
}
