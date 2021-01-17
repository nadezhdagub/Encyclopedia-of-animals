package com.trkpo.animal.encyclopedia.usecase.area_photo;

import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import com.trkpo.animal.encyclopedia.entity.Type;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import com.trkpo.animal.encyclopedia.repository.AreaPhotoRepository;
import com.trkpo.animal.encyclopedia.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Validated
public class CreateAreaPhotoUsecase {
    private final AnimalInfRepository animalInfRepository;
    private final AreaPhotoRepository areaPhotoRepository;


    public AreaPhoto execute(UUID animalId, AreaPhoto dto) {

        return animalInfRepository.findById(animalId)
                .map(id -> {
                    dto.setId(UUID.randomUUID());
                    dto.setAnimal_id(id.getId());
                    return areaPhotoRepository.save(dto);
                })
                .orElseThrow(() -> new NotFoundException(String.format("photo of area (%s) not found", animalId.toString() )));
    }
}
