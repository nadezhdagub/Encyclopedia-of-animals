package com.trkpo.animal.encyclopedia.usecase.animal_inf;

import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import com.trkpo.animal.encyclopedia.exceptions.NotFoundException;
import com.trkpo.animal.encyclopedia.repository.AccountRepository;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAnimalInfUsecase {
    private final AnimalInfRepository animalInfRepository;
    private final AccountRepository accountRepository;

    public AnimalInf execute(UUID animalInfId, AnimalInf animalInfFromDto) {
        return accountRepository.findById(animalInfId)
                .map(account -> {
                    AnimalInf animalInf = AnimalInf.builder()
                            .id(UUID.randomUUID())
                            .build();
                    return animalInfRepository.save(animalInf);
                })
                .orElseThrow(() -> new NotFoundException(String.format("animal(%s) information not found", animalInfId.toString() )));
    }

}
