package com.trkpo.animal.encyclopedia.usecase.type;

import com.trkpo.animal.encyclopedia.api.model.type.UpdateTypeDto;
import com.trkpo.animal.encyclopedia.entity.Type;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import com.trkpo.animal.encyclopedia.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class CreateTypeUsecase {
    private final AnimalInfRepository animalInfRepository;
    private final TypeRepository typeRepository;


    public Type execute(UUID animalId, Type dto) {

        return animalInfRepository.findById(animalId)
                .map(id -> {
                    dto.setId(UUID.randomUUID());
                    dto.setAnimal_id(id.getId());
                    return typeRepository.save(dto);
                })
                .orElseThrow(() -> new NotFoundException(String.format("type(%s) not found", animalId.toString() )));
    }
}
