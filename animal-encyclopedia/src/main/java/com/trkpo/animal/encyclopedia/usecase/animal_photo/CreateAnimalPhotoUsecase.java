package com.trkpo.animal.encyclopedia.usecase.animal_photo;

import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import com.trkpo.animal.encyclopedia.repository.AnimalPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class CreateAnimalPhotoUsecase {
    private final AnimalInfRepository animalInfRepository;
    private final AnimalPhotoRepository animalPhotoRepository;


    public AnimalPhoto execute(UUID animalId, AnimalPhoto dto) {

        return animalInfRepository.findById(animalId)
                .map(id -> {
                    dto.setId(UUID.randomUUID());
                    dto.setAnimal_id(id.getId());
                    return animalPhotoRepository.save(dto);
                })
                .orElseThrow(() -> new NotFoundException(String.format("photo of area (%s) not found", animalId.toString() )));
    }
}
