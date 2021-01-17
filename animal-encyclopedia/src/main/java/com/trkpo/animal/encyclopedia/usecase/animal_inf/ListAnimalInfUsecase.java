package com.trkpo.animal.encyclopedia.usecase.animal_inf;

import com.trkpo.animal.encyclopedia.entity.AnimalInf;
import com.trkpo.animal.encyclopedia.repository.AnimalInfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAnimalInfUsecase {
    private final AnimalInfRepository animalInfRepository;

    public List<AnimalInf> execute() {

        return animalInfRepository.findAll();
    }
}
