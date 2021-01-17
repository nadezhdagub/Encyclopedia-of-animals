package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.AnimalPhotoApi;
import com.trkpo.animal.encyclopedia.api.model.animal_photo.AnimalPhotoDto;
import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import com.trkpo.animal.encyclopedia.exceptions.UnprocessableEntityException;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.animal_photo.CreateAnimalPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_photo.DeleteAnimalPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_photo.ListAnimalPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_photo.mapper.AnimalPhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AnimalPhotoApiController implements AnimalPhotoApi {
    private ListAnimalPhotoUsecase listAnimalPhotoUsecase;
    private CreateAnimalPhotoUsecase createAnimalPhotoUsecase;
    private DeleteAnimalPhotoUsecase deleteAnimalPhotoUsecase;
    private AnimalPhotoMapper animalPhotoMapper;


    @Override
    public ResponseEntity<List<AnimalPhotoDto>> list() {
        var result = listAnimalPhotoUsecase.execute().stream()
                .map(animalPhotoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<AnimalPhotoDto> create(AnimalPhoto animalPhoto) throws Exception {
        if(StringUtils.isEmpty(animalPhoto.getAnimal_photo()))
            throw new UnprocessableEntityException("Photo of area cannot be empty");
        return SecurityHelper.currentAccount()
                .map(animalId -> createAnimalPhotoUsecase.execute(animalId, animalPhoto))
                .map(animalPhotoMapper::toDto)
                .map(animalPhotoDto -> ResponseEntity.status(HttpStatus.CREATED).body(animalPhotoDto))
                .orElse(ResponseEntity.noContent().build());
    }



    @Override
    public ResponseEntity<Void> delete(UUID animalPhotoId) throws Exception {
        deleteAnimalPhotoUsecase.execute(animalPhotoId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
