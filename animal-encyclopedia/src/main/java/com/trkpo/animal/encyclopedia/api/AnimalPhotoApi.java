package com.trkpo.animal.encyclopedia.api;

import com.trkpo.animal.encyclopedia.api.model.animal_photo.AnimalPhotoDto;
import com.trkpo.animal.encyclopedia.api.model.area_photo.AreaPhotoDto;
import com.trkpo.animal.encyclopedia.entity.AnimalPhoto;
import com.trkpo.animal.encyclopedia.entity.AreaPhoto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(value = "Animal_photo API", tags = "Animal_photo API")
public interface AnimalPhotoApi {
    @GetMapping("/photo")
    @ApiOperation(value = "List photo",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok")
    })
    ResponseEntity<List<AnimalPhotoDto>> list();

    @PutMapping("/photo")
    @ApiOperation(value = "Add new photo",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 422, message = "Unprocessable Entity")
    })

    ResponseEntity<AnimalPhotoDto> create(AnimalPhoto animalPhoto) throws Exception;

    @DeleteMapping("/photo/{id}")
    @ApiOperation(value = "Remove photo",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content")
    })
    ResponseEntity<Void> delete(@PathVariable("id") UUID id) throws Exception;



}
