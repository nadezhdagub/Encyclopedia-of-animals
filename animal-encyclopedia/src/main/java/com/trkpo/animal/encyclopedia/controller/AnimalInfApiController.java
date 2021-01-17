package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.AnimalInfApi;
import com.trkpo.animal.encyclopedia.api.model.animal_inf.AnimalInfDto;
import com.trkpo.animal.encyclopedia.exceptions.UnprocessableEntityException;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.CreateAnimalInfUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.DeleteAnimalInfUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.ListAnimalInfUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.UpdateAnimalInfUsecase;
import com.trkpo.animal.encyclopedia.usecase.animal_inf.mapper.AnimalInfMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public abstract class AnimalInfApiController implements AnimalInfApi {
    private final ListAnimalInfUsecase listAnimalInfUsecase;
    private final CreateAnimalInfUsecase createAnimalInfUsecase;
    private final UpdateAnimalInfUsecase updateAnimalInfUsecase;
    private final DeleteAnimalInfUsecase deleteAnimalInfUsecase;
    private final AnimalInfMapper animalInfMapper;

    @Override
    public ResponseEntity<List<AnimalInfDto>> list() {
        return ResponseEntity.ok(listAnimalInfUsecase.execute().stream()
                .map(animalInfMapper::toDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<AnimalInfDto> create(AnimalInfDto dto) {
        if(StringUtils.isEmpty(dto.getInf_of_type()))
            throw new UnprocessableEntityException("The information type cannot be empty");
        return SecurityHelper.currentAccount()
                .map(typeId -> createAnimalInfUsecase.execute(typeId, animalInfMapper.createAnimalInfFromDto(dto)))
                .map(animalInfMapper::toDto)
                .map(companyDto -> ResponseEntity.status(HttpStatus.CREATED).body(companyDto))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<AnimalInfDto> update(UUID animalInfId, AnimalInfDto dto) {
        if(StringUtils.isEmpty(dto.getInf_of_type()))
            throw new UnprocessableEntityException("The information type cannot be empty");
        return SecurityHelper.currentAccount()
                .map(account -> updateAnimalInfUsecase.execute(animalInfId, dto))
                .map(animalInfMapper::toDto)
                .map(car -> ResponseEntity.status(HttpStatus.CREATED).body(car))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<Void> delete(UUID accountId) {
        deleteAnimalInfUsecase.execute(accountId);
        log.info("Account {} successfully deleted.", accountId);
        return ResponseEntity.noContent().build();
    }
}
