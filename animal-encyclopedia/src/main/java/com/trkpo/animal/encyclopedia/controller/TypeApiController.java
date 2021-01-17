package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.TypeApi;
import com.trkpo.animal.encyclopedia.api.model.type.TypeDto;

import com.trkpo.animal.encyclopedia.entity.Type;
import com.trkpo.animal.encyclopedia.exceptions.UnprocessableEntityException;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.type.CreateTypeUsecase;
import com.trkpo.animal.encyclopedia.usecase.type.DeleteTypeUsecase;
import com.trkpo.animal.encyclopedia.usecase.type.ListTypeUsecase;
import com.trkpo.animal.encyclopedia.usecase.type.UpdateTypeUsecase;
import com.trkpo.animal.encyclopedia.usecase.type.mapper.TypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public abstract class TypeApiController implements TypeApi {
    private ListTypeUsecase listTypeUsecase;
    private CreateTypeUsecase createTypeUsecase;
    private UpdateTypeUsecase updateTypeUsecase;
    private DeleteTypeUsecase deleteTypeUsecase;
    private TypeMapper typeMapper;


    @Override
    public ResponseEntity<List<TypeDto>> list() {
        var result = listTypeUsecase.execute().stream()
                .map(typeMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<TypeDto> create(Type type) throws Exception {
        if(StringUtils.isEmpty(type.getName()))
            throw new UnprocessableEntityException("Type cannot be empty");
        return SecurityHelper.currentAccount()
                .map(typeId -> createTypeUsecase.execute(typeId, type))
                .map(typeMapper::toDto)
                .map(companyDto -> ResponseEntity.status(HttpStatus.CREATED).body(companyDto))
                .orElse(ResponseEntity.noContent().build());
    }


    @Override
    public ResponseEntity<TypeDto> update(UUID typeId, TypeDto typeDto) throws Exception {
        if(StringUtils.isEmpty(typeDto.getName()))
            throw new UnprocessableEntityException("type cannot be empty");
        return Optional.ofNullable(updateTypeUsecase.execute(typeId, typeDto))
                .map(typeMapper::toDto)
                .map(tpDto -> ResponseEntity.status(HttpStatus.CREATED).body(tpDto))
                .orElse(ResponseEntity.noContent().build());
    }

    @Override
    public ResponseEntity<Void> delete(UUID typeId) throws Exception {
        deleteTypeUsecase.execute(typeId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
