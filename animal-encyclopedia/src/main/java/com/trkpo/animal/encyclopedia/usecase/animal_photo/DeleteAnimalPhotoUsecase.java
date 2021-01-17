package com.trkpo.animal.encyclopedia.usecase.animal_photo;

import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAnimalPhotoUsecase {
    private final AnimalPhotoRepository animalPhotoRepository;

    public void execute(UUID animalId) {
        animalPhotoRepository.findById(animalId)
                .map(animal -> {
                    animalPhotoRepository.delete(animal);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("photo of area(%s) not found", animalId.toString())));
    }
}
