package com.trkpo.animal.encyclopedia.usecase.animal_inf;

import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteAnimalInfUsecase {
    private final AnimalInfRepository animalInfRepository;

    public void execute(UUID animalInfId) {
        animalInfRepository.findById(animalInfId)
                .map(animalInf -> {
                    animalInfRepository.delete(animalInf);
                    return true;
                })
                .orElseThrow(() -> new NotFoundException(String.format("company(%s) not found", animalInfId.toString())));
    }

}
