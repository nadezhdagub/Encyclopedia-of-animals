package com.trkpo.animal.encyclopedia.usecase.animal_photo;

import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import com.trkpo.animal.encyclopedia.repository.AnimalPhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListAnimalPhotoUsecase {
    private final AnimalPhotoRepository animalPhotoRepository;

    public List<AnimalPhoto> execute() {
        return animalPhotoRepository.findAll();
    }
}
