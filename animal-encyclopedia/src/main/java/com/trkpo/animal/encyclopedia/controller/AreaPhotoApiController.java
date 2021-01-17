package com.trkpo.animal.encyclopedia.controller;

import com.trkpo.animal.encyclopedia.api.AreaPhotoApi;
import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import com.trkpo.animal.encyclopedia.exceptions.UnprocessableEntityException;
import com.trkpo.animal.encyclopedia.security.SecurityHelper;
import com.trkpo.animal.encyclopedia.usecase.area_photo.CreateAreaPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.area_photo.DeleteAreaPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.area_photo.ListAreaPhotoUsecase;
import com.trkpo.animal.encyclopedia.usecase.area_photo.mapper.AreaPhotoMapper;

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
public class AreaPhotoApiController implements AreaPhotoApi {
    private ListAreaPhotoUsecase listAreaPhotoUsecase;
    private CreateAreaPhotoUsecase createAreaPhotoUsecase;
    private DeleteAreaPhotoUsecase deleteAreaPhotoUsecase;
    private AreaPhotoMapper areaPhotoMapper;


    @Override
    public ResponseEntity<List<AreaPhotoDto>> list() {
        var result = listAreaPhotoUsecase.execute().stream()
                .map(areaPhotoMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


    @Override
    public ResponseEntity<AreaPhotoDto> create(AreaPhoto areaPhoto) throws Exception {
        if(StringUtils.isEmpty(areaPhoto.getArea_photo()))
            throw new UnprocessableEntityException("Photo of area cannot be empty");
        return SecurityHelper.currentAccount()
                .map(typeId -> createAreaPhotoUsecase.execute(typeId, areaPhoto))
                .map(areaPhotoMapper::toDto)
                .map(areaPhotoDto -> ResponseEntity.status(HttpStatus.CREATED).body(areaPhotoDto))
                .orElse(ResponseEntity.noContent().build());
    }



    @Override
    public ResponseEntity<Void> delete(UUID areaPhotoId) throws Exception {
        deleteAreaPhotoUsecase.execute(areaPhotoId);
        return ResponseEntity
                .noContent()
                .build();
    }
}
